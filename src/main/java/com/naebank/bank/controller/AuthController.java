package com.naebank.bank.controller;

import com.naebank.bank.config.TokenProvider;
import com.naebank.bank.controller.dto.CredentialDto;
import com.naebank.bank.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<String> register(@RequestBody CredentialDto credentialDto) {
        userService.createUser(
                credentialDto.getEmail(),
                credentialDto.getPassword(),
                credentialDto.getName(),
                credentialDto.getRole()
        );

        return ResponseEntity.ok("User " + credentialDto.getEmail() + " was created!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CredentialDto credentialDto) {

        if (userService.validateCredentials(credentialDto.getEmail(),
                credentialDto.getPassword())) {
            return ResponseEntity.ok(tokenProvider.createToken(credentialDto.getEmail()));
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
