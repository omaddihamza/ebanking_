package net.me.ebanking.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.me.ebanking.dto.*;
import net.me.ebanking.entitie.*;
import net.me.ebanking.enums.AccountStatus;
import net.me.ebanking.enums.OperationType;
import net.me.ebanking.exception.AccountNotFundException;
import net.me.ebanking.exception.BanlanceNotSufficentException;
import net.me.ebanking.exception.CustomerNotFundException;
import net.me.ebanking.mapper.AccountOperationMapper;
import net.me.ebanking.mapper.BankaccountMapper;
import net.me.ebanking.mapper.CustomerMapper;
import net.me.ebanking.repository.AccountOperationRepository;
import net.me.ebanking.repository.BankAccountRepository;
import net.me.ebanking.repository.CustomerRepository;
import net.me.ebanking.utils.AccountNumber;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class BankAccountServiceImpl implements BankAccountService {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private CustomerMapper customerMapper;
    private BankaccountMapper bankaccountMapper;
    private AccountOperationMapper accountOperationMapper;


    @Override
    public Customer save(CustomerDto customerDto) {
         Customer customer = customerMapper.fromCustomerDto(customerDto);
         customer = customerRepository.save(customer);
        return customer;
    }

    @Override
    public List<CustomerDto> customerList() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = customers.stream().map(c->
                customerMapper.fromCustomer(c)
        ).collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public CustomerDto getCustomer(Long id) throws CustomerNotFundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFundException("Customer not fund"));
        CustomerDto customerDto = customerMapper.fromCustomer(customer);
        return customerDto;
    }

    @Override
    public void updateCustomer(Long id, CustomerDto customerDto) throws CustomerNotFundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFundException("Customer not fund"));
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        System.out.println(customer);
    }

    @Override
    public void deleteCustomer(Long id) throws CustomerNotFundException {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFundException("Customer not fund"));
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerDto> getCustomerByKeyword(String keyword) {
        List<Customer> customers = customerRepository.searchCustomer(keyword);
        List<CustomerDto> customerDtos =  customers.stream().map(c->customerMapper.fromCustomer(c)).collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public SavingAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer==null){
            throw new CustomerNotFundException("customer not found");
        }

        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setAccountNumber(AccountNumber.generateAccountNumber());
        savingAccount.setStatus(AccountStatus.CREATED);
        savingAccount.setCurrency("DH");
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);
        SavingAccount saveAccount = bankAccountRepository.save(savingAccount);
        return bankaccountMapper.fromSavingAccount(saveAccount);
    }

    @Override
    public CurrentAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFundException {
        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer==null){
            throw new CustomerNotFundException("customer not found");
        }
        CurrentAccount currentAccount = new CurrentAccount();
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setAccountNumber(AccountNumber.generateAccountNumber());
        currentAccount.setStatus(AccountStatus.CREATED);
        currentAccount.setCurrency("DH");
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);
        CurrentAccount savaAccount = bankAccountRepository.save(currentAccount);
        return bankaccountMapper.fromCurrentAccount(savaAccount);
    }



    @Override
    public BankAccountDto getBankAccount(String accountNumber) throws AccountNotFundException {
        BankAccount bankAccount = bankAccountRepository.getBankAccountsByAccountNumber(accountNumber);
        if(bankAccount==null){
            throw new AccountNotFundException("account with "+accountNumber+ "not found");
        }

        if(bankAccount instanceof SavingAccount){
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return bankaccountMapper.fromSavingAccount(savingAccount);
        }else{
            CurrentAccount currentAccount = (CurrentAccount) bankAccount;
            return bankaccountMapper.fromCurrentAccount(currentAccount);
        }
    }

    @Override
    public List<BankAccountDto> bankAccountList() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountDto>  bankAccountDtoList =    bankAccounts.stream().map(bankAccount -> {
            if(bankAccount instanceof CurrentAccount){
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return bankaccountMapper.fromCurrentAccount(currentAccount);
            }else{
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return bankaccountMapper.fromSavingAccount(savingAccount);
            }
        }).collect(Collectors.toList());

        return bankAccountDtoList;
    }

    @Override
    public void debit(String accountNumber, double amount, String description) throws AccountNotFundException, BanlanceNotSufficentException {
       BankAccount bankAccount = bankAccountRepository.getBankAccountsByAccountNumber(accountNumber);
        if(bankAccount.getBalance() < amount){
           throw new BanlanceNotSufficentException("banlance not sufficent");
        }
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()-amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void credit(String accountNumber, double amount, String description) throws AccountNotFundException {
        BankAccount bankAccount = bankAccountRepository.getBankAccountsByAccountNumber(accountNumber);
        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setDescription(description);
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
        bankAccount.setBalance(bankAccount.getBalance()+amount);
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void transfer(String accountNumberSource, String accountNumberDestination, double amount) throws BanlanceNotSufficentException, AccountNotFundException {
           debit(accountNumberSource, amount, "Transfer to"+accountNumberDestination);
           credit(accountNumberDestination, amount, "Transfer from "+accountNumberSource);
    }

    @Override
    public List<AccountOperationDto> findOperationsByAccount(Long id) {
        List<AccountOperation> accountOperations = accountOperationRepository.findByBankAccount_Id(id);
        List<AccountOperationDto> accountOperationDto = accountOperations.stream().map(accountOperation -> accountOperationMapper.fromAccountOperation(accountOperation)).collect(Collectors.toList());
        return accountOperationDto;

    }

    @Override
    public AccountHistoryDto gatAccountHistory(Long id, int page, int size) throws AccountNotFundException {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if(bankAccount ==null) throw new AccountNotFundException("acount not found");
        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccount_Id(id, PageRequest.of(page, size));
        AccountHistoryDto accountHistoryDto = new AccountHistoryDto();
        List<AccountOperationDto> accountOperationDtos = accountOperations.getContent().stream().map(op -> accountOperationMapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDto.setAccountOperationDtos(accountOperationDtos);
        accountHistoryDto.setAccountId(bankAccount.getId());
        accountHistoryDto.setBalance(bankAccount.getBalance());
        accountHistoryDto.setTotalPage(accountOperations.getTotalPages());
        accountHistoryDto.setCurrentPage(page);
        accountHistoryDto.setPageSize(size);
        return accountHistoryDto;
    }

}
