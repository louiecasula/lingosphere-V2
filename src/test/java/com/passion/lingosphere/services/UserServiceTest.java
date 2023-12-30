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
import javax.naming.AuthenticationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private User user;
    @InjectMocks
    private UserService userService;
    private UserDto userDto;

    @BeforeEach
    public void setup() {
        String username = "testuser";
        String email = "user@lingo.com";
        String password = "zipcode";
        userDto = new UserDto(username, email, password);
//        user = new User(username, email, password);
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
    public void registerUserUsernameTakenTest() {
        given(userRepository.existsByUsername(userDto.getUsername())).willReturn(true);

        assertThrows(UsernameAlreadyExistsException.class, () -> userService.registerUser(userDto));
    }

    @Test
    public void registerUserEmailTakenTest() {
        given(userRepository.existsByEmail(userDto.getEmail())).willReturn(true);

        assertThrows(EmailAlreadyExistsException.class, () -> userService.registerUser(userDto));
    }

    @Test
    public void authenticateUserSuccessfulTest() throws AuthenticationException {
        // Mocked user values
        when(user.getUsername()).thenReturn(userDto.getUsername());
        when(user.getPassword()).thenReturn("encodedPassword");

        given(userRepository.findByUsername(userDto.getUsername())).willReturn(Optional.of(user));
        given(passwordEncoder.matches(userDto.getPassword(), "encodedPassword")).willReturn(true);

        User authenticatedUser = userService.authenticateUser(userDto.getUsername(), userDto.getPassword());

        assertNotNull(authenticatedUser);
        assertEquals(userDto.getUsername(), authenticatedUser.getUsername());
    }

    @Test
    public void authenticateUserWrongUsername() {
        given(userRepository.findByUsername(userDto.getUsername())).willReturn(Optional.empty());

        assertThrows(AuthenticationException.class, () -> userService.authenticateUser(userDto.getUsername(), userDto.getPassword()));
    }

    @Test
    public void authenticateUserWrongPassword() {
        given(userRepository.findByUsername(userDto.getUsername())).willReturn(Optional.of(user));
        given(passwordEncoder.matches(userDto.getPassword(), user.getPassword())).willReturn(false);

        assertThrows(AuthenticationException.class, () -> userService.authenticateUser(userDto.getUsername(), userDto.getPassword()));
    }
}
