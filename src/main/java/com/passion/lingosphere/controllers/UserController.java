package com.passion.lingosphere.controllers;

import com.passion.lingosphere.dtos.UserDto;
import com.passion.lingosphere.exceptions.EmailAlreadyExistsException;
import com.passion.lingosphere.exceptions.UsernameAlreadyExistsException;
import com.passion.lingosphere.models.User;
import com.passion.lingosphere.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        try {
            User newUser = userService.registerUser(userDto);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("User registration failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
