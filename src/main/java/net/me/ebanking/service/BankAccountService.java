package net.me.ebanking.service;

import net.me.ebanking.dto.*;
import net.me.ebanking.entitie.AccountOperation;
import net.me.ebanking.entitie.Customer;
import net.me.ebanking.exception.AccountNotFundException;
import net.me.ebanking.exception.BanlanceNotSufficentException;
import net.me.ebanking.exception.CustomerNotFundException;

import java.util.List;


public interface BankAccountService {
    Customer save(CustomerDto customerDto);
    List<CustomerDto> customerList();
    CustomerDto getCustomer(Long id) throws CustomerNotFundException;
    void updateCustomer(Long id, CustomerDto customerDto) throws CustomerNotFundException;
    void deleteCustomer(Long id) throws CustomerNotFundException;
    List<CustomerDto> getCustomerByKeyword(String keyword);



    SavingAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFundException;
    CurrentAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFundException;
    BankAccountDto getBankAccount(String accountNumber) throws AccountNotFundException;
    List<BankAccountDto> bankAccountList();



    void debit(String accountNumber, double amount, String description) throws AccountNotFundException, BanlanceNotSufficentException;
    void credit(String accountNumber, double amount, String description) throws AccountNotFundException;
    void transfer(String accountNumberSource, String accountNumberDestination, double amount) throws BanlanceNotSufficentException, AccountNotFundException;
    List<AccountOperationDto> findOperationsByAccount(Long id);


    AccountHistoryDto gatAccountHistory(String accountNumber, int page, int size) throws AccountNotFundException;
}
