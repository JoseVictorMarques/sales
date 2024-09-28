package com.example.sales.tests;

import com.example.sales.business.UserBusiness;
import com.example.sales.model.dtos.UserDTO;
import com.example.sales.model.entities.User;
import com.example.sales.repository.UserRepository;
import com.example.sales.utils.PasswordUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserTest {

    @InjectMocks
    private UserBusiness userBusiness;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User("john", "password123");
        User savedUser = new User("john", PasswordUtils.hashPassword("password123"));

        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        User result = userBusiness.registerUser(user);

        assertNotNull(result);
        assertEquals(savedUser.getUsername(), result.getUsername());
        assertNotEquals("password123", result.getPassword());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User("john", "password123");
        User userDB = new User("john", PasswordUtils.hashPassword("password123"));

        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(userDB);
        when(PasswordUtils.verifyPassword(user.getPassword(), userDB.getPassword())).thenReturn(true);

        Boolean loginResult = userBusiness.login(user);

        assertTrue(loginResult);
    }

    @Test
    public void testChangePasswordSuccess() {
        UserDTO userDTO = new UserDTO(null, "john", null,"newPassword");
        User user = new User("john", PasswordUtils.hashPassword("oldPassword"));

        when(userRepository.findUserByUsername(userDTO.getUsername())).thenReturn(user);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userBusiness.changePassword(userDTO);

        assertNotNull(result);
        assertEquals(userDTO.getUsername(), result.getUsername());
        assertNotEquals("oldPassword", result.getPassword());
    }
}