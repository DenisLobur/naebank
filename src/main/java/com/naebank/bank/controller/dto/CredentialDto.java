package com.naebank.bank.controller.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CredentialDto {
    private String email;
    private String password;
    private String name;
    private String role;
}
