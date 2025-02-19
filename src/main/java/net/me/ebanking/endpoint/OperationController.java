package net.me.ebanking.endpoint;

import lombok.AllArgsConstructor;

import net.me.ebanking.dto.AccountHistoryDto;
import net.me.ebanking.dto.AccountOperationDto;
import net.me.ebanking.exception.AccountNotFundException;
import net.me.ebanking.exception.BanlanceNotSufficentException;
import net.me.ebanking.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@AllArgsConstructor
@CrossOrigin("*")
public class OperationController {
    private BankAccountService bankAccountService;

    @PostMapping("transfer")
    public void transfer(String accountNumberSource, String accountNumberDestination, double amount) throws BanlanceNotSufficentException, AccountNotFundException {
        bankAccountService.transfer(accountNumberSource, accountNumberDestination, amount);
    }

    @PostMapping("credit")
    public void credit(String accountNumber, double amount, String description) throws AccountNotFundException {
        bankAccountService.credit(accountNumber, amount, description);
    }

    @PostMapping("debit")
    public void debit(String accountNumber, double amount, String description) throws AccountNotFundException, BanlanceNotSufficentException {
        bankAccountService.debit(accountNumber, amount, description);
    }

    @GetMapping("operation/{id}/account")
    public List<AccountOperationDto> findOperationsByAccount(@PathVariable Long id) {
        return bankAccountService.findOperationsByAccount(id);
    }

    @GetMapping("operation/{accountNumber}/pageOperation")
    public AccountHistoryDto getAccountHistory(@PathVariable String accountNumber, @RequestParam("page") int page, @RequestParam("size") int size) throws AccountNotFundException {
        return bankAccountService.gatAccountHistory(accountNumber, page, size);
    }
}
