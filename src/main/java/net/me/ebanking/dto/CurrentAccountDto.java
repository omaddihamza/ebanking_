package net.me.ebanking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import net.me.ebanking.entitie.AppUser;
import net.me.ebanking.enums.AccountStatus;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentAccountDto extends BankAccountDto {
    private Long id;
    private Date createdAt;
    private double balance;
    private AccountStatus status;
    private String accountNumber;
    private String currency;
    private CustomerDto customerDto;
    private double overDraft;
    private AppUser createdBy;
}
