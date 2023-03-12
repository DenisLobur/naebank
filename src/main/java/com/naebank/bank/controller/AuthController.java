package com.naebank.bank.controller;

import com.naebank.bank.config.TokenProvider;
import com.naebank.bank.controller.dto.AuthDto;
import com.naebank.bank.controller.dto.CredentialDto;
import com.naebank.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenProvider tokenProvider;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@RequestBody CredentialDto credentialDto) {
        userService.createUser(
                credentialDto.getEmail(),
                credentialDto.getPassword(),
                credentialDto.getName(),
                credentialDto.getRole()
        );

        AuthDto authDto = new AuthDto(tokenProvider.createToken(credentialDto.getEmail()), null);
        return new ResponseEntity<>(authDto, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthDto> login(@RequestBody CredentialDto credentialDto) {
        AuthDto authDto;
        if (userService.validateCredentials(credentialDto.getEmail(), credentialDto.getPassword())) {
            authDto = new AuthDto(tokenProvider.createToken(credentialDto.getEmail()), null);
            return new ResponseEntity<>(authDto, HttpStatus.OK);
        }

        authDto = new AuthDto("", "Invalid credentials");
        return new ResponseEntity<>(authDto, HttpStatus.BAD_REQUEST);
    }
}
