package com.naebank.bank.controller.dto;

import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private Integer amount;
    private String type; // top-up/withdrawal
    private String cardName; // visa 1234
    private String status; // pending, done, rejected
    private Long date; // HH:mm:ss
}
