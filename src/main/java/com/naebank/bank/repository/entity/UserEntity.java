package com.naebank.bank.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "user", schema = "bank")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CardEntity> cards;
}
