package com.example.sales.controller;

import com.example.sales.business.UserBusiness;
import com.example.sales.model.dtos.UserDTO;
import com.example.sales.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return ResponseEntity.ok(userBusiness.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody User user) {
        return ResponseEntity.ok(userBusiness.login(user));
    }

    @PutMapping("/change-password")
    public ResponseEntity<User> changePassword(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userBusiness.changePassword(userDTO));
    }
}