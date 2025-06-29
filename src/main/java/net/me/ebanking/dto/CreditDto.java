package net.me.ebanking.dto;

import lombok.Data;


@Data
public class CreditDto {
    private String accountNumber;
    private double amount;
    private String description;
}
