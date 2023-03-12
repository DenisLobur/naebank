package com.naebank.bank.service;

import com.naebank.bank.controller.dto.UserPr;
import com.naebank.bank.repository.UserRepository;
import com.naebank.bank.repository.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder cryptPasswordEncoder;

    public void createUser(String email, String password, String name, String role) {

        String encodedPassword = cryptPasswordEncoder.encode(password);

        UserEntity newUser = new UserEntity();
        newUser.setEmail(email);
        newUser.setPassword(encodedPassword);
        newUser.setName(name);
        newUser.setRole(role);
        userRepository.save(newUser);
    }

    public boolean validateCredentials(String email, String password) {
        String encodedPassword = userRepository.findByEmail(email)
                .map(UserEntity::getPassword)
                .orElse("");

        return cryptPasswordEncoder.matches(password, encodedPassword);
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    //TODO: rewrite
    public UserEntity getCurrentUser() {
        long id = getCurrentUserId();
        return userRepository.findById(id).get();
    }

    private long getCurrentUserId() {
        String email = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return userRepository.findByEmail(email).get().getId();
    }

}
