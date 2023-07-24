package com.example.hw49.controller;

import com.example.hw49.dto.UserDto;
import com.example.hw49.entity.User;
import com.example.hw49.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public void addNewUser(@RequestBody UserDto user){
        userService.addNewUser(user);
    }
}