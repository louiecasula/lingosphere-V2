package com.passion.lingosphere.services;

import com.passion.lingosphere.dtos.UserDto;
import com.passion.lingosphere.exceptions.EmailAlreadyExistsException;
import com.passion.lingosphere.exceptions.UsernameAlreadyExistsException;
import com.passion.lingosphere.models.User;
import com.passion.lingosphere.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    public void setup() {
        userDto = new UserDto();
        userDto.setUsername("testuser");
        userDto.setEmail("user@lingo.com");
        userDto.setPassword("zipcode");
    }

    @Test
    public void registerUserSuccessfulTest() throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        given(userRepository.existsByUsername(userDto.getUsername())).willReturn(false);
        given(userRepository.existsByEmail(userDto.getEmail())).willReturn(false);
        given(userRepository.save(any(User.class))).willReturn(new User(userDto.getUsername(), userDto.getEmail(), userDto.getPassword()));

        User registered = userService.registerUser(userDto);

        assertNotNull(registered);
        assertEquals(userDto.getUsername(), registered.getUsername());
    }

    @Test
    public void registerUserUsernameTakenTest() throws UsernameAlreadyExistsException {
        given(userRepository.existsByUsername(userDto.getUsername())).willReturn(true);

        assertThrows(UsernameAlreadyExistsException.class, () -> userService.registerUser(userDto));
    }

    @Test
    public void registerUserEmailTakenTest() throws EmailAlreadyExistsException {
        given(userRepository.existsByEmail(userDto.getEmail())).willReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(userDto));
    }
}