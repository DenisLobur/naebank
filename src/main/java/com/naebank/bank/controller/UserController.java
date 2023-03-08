package com.naebank.bank.controller;

import com.naebank.bank.controller.dto.UserDto;
import com.naebank.bank.mapper.UserMapper;
import com.naebank.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = UserMapper.INSTANCE.toDtoList(userService.getAllUsers());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
