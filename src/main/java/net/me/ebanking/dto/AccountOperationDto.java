package net.me.ebanking.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import net.me.ebanking.enums.OperationType;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccountOperationDto {
    private Long id;
    private Date date;
    private double amount;
    private String description;
    private OperationType type;
}
