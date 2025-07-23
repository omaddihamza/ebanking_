package net.me.ebanking.endpoint;

import lombok.AllArgsConstructor;
import net.me.ebanking.dto.BankAccountDto;
import net.me.ebanking.dto.CurrentAccountDto;
import net.me.ebanking.dto.SavingAccountDto;
import net.me.ebanking.exception.AccountNotFundException;
import net.me.ebanking.exception.CustomerNotFundException;
import net.me.ebanking.service.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin("*")
public class BankaccountController {

    private BankAccountService bankAccountService;

    @PostMapping("current")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public CurrentAccountDto saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotFundException {
        return bankAccountService.saveCurrentBankAccount(initialBalance, overDraft, customerId);
    }

    @PostMapping("saving")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public SavingAccountDto saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotFundException {
        return bankAccountService.saveSavingBankAccount(initialBalance, interestRate, customerId);
    }

    @GetMapping("bankaccount/{accountNumber}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public BankAccountDto getBankAccount(@PathVariable String accountNumber) throws AccountNotFundException {
        return bankAccountService.getBankAccount(accountNumber);
    }

    @GetMapping("bankaccounts")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<BankAccountDto> bankAccountList() {
        return bankAccountService.bankAccountList();
    }
}
