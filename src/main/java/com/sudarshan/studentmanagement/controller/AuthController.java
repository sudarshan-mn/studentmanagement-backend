package com.sudarshan.studentmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sudarshan.studentmanagement.dto.LoginRequestDTO;
import com.sudarshan.studentmanagement.dto.RegisterRequestDTO;
import com.sudarshan.studentmanagement.model.User;
import com.sudarshan.studentmanagement.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequestDTO dto) {
        return userService.register(dto);
    }

    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginRequestDTO dto) {
        return userService.login(dto.getUsername(), dto.getPassword());
    }
}
