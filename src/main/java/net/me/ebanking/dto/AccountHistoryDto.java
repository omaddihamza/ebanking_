package net.me.ebanking.dto;

import lombok.Data;

import java.util.List;


@Data
public class AccountHistoryDto {
    private Long accountId;
    private double balance;
    private String accountNumber;
    private int currentPage;
    private int totalPage;
    private int pageSize;
    private List<AccountOperationDto> accountOperationDtos;
}
