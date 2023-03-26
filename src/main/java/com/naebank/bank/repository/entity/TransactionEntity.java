package com.naebank.bank.repository.entity;

import jakarta.persistence.*;
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

    @Column
    private Integer amount;

    @Column
    private String type; // top-up/withdrawal

    @Column
    private String cardName; // visa 1234

    @Column
    private String status; // pending, done, rejected

    @Column
    private Long date; // HH:mm:ss

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private CardEntity card;


}
