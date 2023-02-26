package com.naebank.bank.repository.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "transaction", schema = "bank")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(scale = 2, precision = 10)
    private Double amount;

    @Column
    private String currencyFrom; // UAH-USD

    @Column
    private String currencyTo; // UAH-USD

    @Column
    private String status; // Passed, Failed, Pending, Declined


}
