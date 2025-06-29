package net.me.ebanking.dto;

import lombok.Data;

@Data
public class TransferDto {
    private String accountNumberSource;
    private String accountNumberDestination;
    private double amount;
}
