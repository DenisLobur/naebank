package com.naebank.bank.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "card", schema = "bank")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String type; // Visa, MasterCard, AmericanExpress, etc

    @Column
    private Integer cardMask; // **** **** **** 1234

    @Column
    private Integer expMonth; // 01 - 12

    @Column
    private Integer expYear; // 2022 - 2032

    @Column
    private Boolean isDefault;

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<TransactionEntity> transactions;
}
