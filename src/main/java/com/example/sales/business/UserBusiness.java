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

    public User registerUser(User user) {
        String hashPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }

    public Boolean login(User user) {
        User userDB = new User();
        try {
           userDB = userRepository.findUserByUsername(user.getUsername());
        }catch(Exception e){
            throw new RuntimeException(" Username does not exists! ");
        }
        return  PasswordUtils.verifyPassword(user.getPassword(),userDB.getPassword());
    }
}