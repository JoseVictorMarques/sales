package com.example.sales.controller;

import com.example.sales.business.UserBusiness;
import com.example.sales.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/cadastro")
    public ResponseEntity<User> cadastrarUsuario(@RequestBody User user) {
        User newUser = userBusiness.cadastrarUsuario(user);
        return ResponseEntity.ok(newUser);
    }
}