package com.passion.lingosphere.services;

import com.passion.lingosphere.dtos.UserDto;
import com.passion.lingosphere.exceptions.EmailAlreadyExistsException;
import com.passion.lingosphere.exceptions.UsernameAlreadyExistsException;
import com.passion.lingosphere.models.User;
import com.passion.lingosphere.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(UserDto userDto) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        // Check if the user already exists by username
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UsernameAlreadyExistsException("Username is taken");
        }
        // Check if user already exists by email
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email is already in use");
        }
        // Create a new User entity from the DTO
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // Save the new user entity
        return userRepository.save(newUser);
    }

    public User authenticateUser(String email, String password) throws AuthenticationException {
        // Check that the user's email is in database
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Email not found"));
        // Check that the user's password is associated with their email
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }
        // Return user
        return user;
    }
}
