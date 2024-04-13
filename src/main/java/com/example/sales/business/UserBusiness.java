package com.example.sales.business;

import com.example.sales.model.entities.User;
import com.example.sales.repository.UserRepository;
import com.example.sales.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {
    @Autowired
    private UserRepository userRepository;

    public User cadastrarUsuario(User user) {
        String hashPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }
}