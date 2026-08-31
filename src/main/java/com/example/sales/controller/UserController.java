package com.example.sales.controller;

import com.example.sales.business.UserBusiness;
import com.example.sales.model.dtos.UserDTO;
import com.example.sales.model.entities.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userBusiness.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userBusiness.login(user));
    }

    @PutMapping("/change-password")
    public ResponseEntity<User> changePassword(@Valid @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userBusiness.changePassword(userDTO));
    }
}