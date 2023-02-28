package com.naebank.bank.controller.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class TransactionDto {
    private Long id;
    private Double amount;
    private String currencyFrom; // UAH-USD
    private String currencyTo; // UAH-USD
    private String status;
}
