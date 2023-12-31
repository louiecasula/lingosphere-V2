package com.passion.lingosphere.services;

import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.Word;
import com.passion.lingosphere.repositories.UserWordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserWordServiceTest {

    @Mock
    private UserWordRepository userWordRepository;
    @Mock
    private User user;
    @Mock
    private Word word;
    @InjectMocks
    private UserWordService userWordService;

    @Test
    public void addUserWordSuccessfulTest() {

    }

    @Test
    public void addUserWordUserDoesntExistTest() {

    }

    @Test
    public void addUserWordWordDoesntExistTest() {

    }
}
