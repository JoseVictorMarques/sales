package com.example.sales.business;

import com.example.sales.model.dtos.UserDTO;
import com.example.sales.model.entities.User;
import com.example.sales.repository.UserRepository;
import com.example.sales.utils.PasswordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBusiness {
    private static final Logger logger = LoggerFactory.getLogger(UserBusiness.class);

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        logger.info("Registering new user: {}", user.getUsername());
        String hashPassword = PasswordUtils.hashPassword(user.getPassword());
        user.setPassword(hashPassword);
        User savedUser = userRepository.save(user);
        logger.info("User registered successfully: {}", user.getUsername());
        return savedUser;
    }

    public Boolean login(User user) {
        logger.info("Login attempt for user: {}", user.getUsername());
        User userDB = userRepository.findUserByUsername(user.getUsername());
        if (userDB == null) {
            logger.warn("Login failed: user not found - {}", user.getUsername());
            throw new RuntimeException("Username does not exist!");
        }
        boolean isPasswordValid = PasswordUtils.verifyPassword(user.getPassword(), userDB.getPassword());
        if (isPasswordValid) {
            logger.info("Login successful for user: {}", user.getUsername());
        } else {
            logger.warn("Login failed: invalid password for user - {}", user.getUsername());
        }
        return isPasswordValid;
    }

    public User changePassword(UserDTO userDTO) {
        logger.info("Changing password for user: {}", userDTO.getUsername());
        User user = userRepository.findUserByUsername(userDTO.getUsername());
        if (user != null) {
            String hashPassword = PasswordUtils.hashPassword(userDTO.getNewPassword());
            user.setPassword(hashPassword);
            user = userRepository.save(user);
            logger.info("Password changed successfully for user: {}", userDTO.getUsername());
        } else {
            logger.warn("Password change failed: user not found - {}", userDTO.getUsername());
        }
        return user;
    }
}