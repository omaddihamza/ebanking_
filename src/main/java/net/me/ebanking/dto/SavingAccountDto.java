package net.me.ebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.me.ebanking.enums.AccountStatus;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SavingAccountDto extends BankAccountDto {
    private Long id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String accountNumber;
    private String currency;
    private CustomerDto customerDto;
    private double interestRate;
}
