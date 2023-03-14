package com.naebank.bank.controller.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CardDto {
    private Long id;

    private String type; // Visa, MasterCard, AmericanExpress, etc

    private Integer cardMask; // **** **** **** 1234

    private Integer expMonth; // 01 - 12

    private Integer expYear; // 2022 - 2032

    private Integer amount; // 10UAH

    private Boolean isDefault;
}
