package com.passion.lingosphere.services;

import com.passion.lingosphere.dtos.UserDto;
import com.passion.lingosphere.models.User;
import com.passion.lingosphere.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private UserDto user;

    @BeforeEach
    public void setup() {
        user = new UserDto();
        user.setUsername("testuser");
        user.setEmail("user@lingo.com");
        user.setPassword("zipcode");
    }

    @Test
    public void registerUserSuccessfulTest() throws Exception {
        Assertions.assertFalse(userRepository.existsByUsername(user.getUsername()));
        Assertions.assertFalse(userRepository.existsByEmail(user.getEmail()));

        User registered = userService.registerUser(user);

        Assertions.assertNotNull(registered);
        Assertions.assertEquals(user.getUsername(), registered.getUsername());
    }
}
