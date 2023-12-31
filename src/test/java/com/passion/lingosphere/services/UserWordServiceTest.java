package com.passion.lingosphere.services;

import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.UserWord;
import com.passion.lingosphere.models.Word;
import com.passion.lingosphere.repositories.UserRepository;
import com.passion.lingosphere.repositories.UserWordRepository;
import com.passion.lingosphere.repositories.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserWordServiceTest {

    @Mock
    private UserWordRepository userWordRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private WordRepository wordRepository;
    @Mock
    private User user;
    @Mock
    private Word word;
    @InjectMocks
    private UserWordService userWordService;

    @Test
    public void addUserWordSuccessfulTest() {
        given(userRepository.findById(0L)).willReturn(Optional.of(user));
        given(wordRepository.findById(0L)).willReturn(Optional.of(word));
        given(userWordRepository.save(any(UserWord.class))).willReturn(new UserWord(user, word, new Date()));

        UserWord added = userWordService.addUserWord(0L, 0L);

        assertNotNull(added);
        assertEquals(user, added.getUser());
        assertEquals(word, added.getWord());
    }

    @Test
    public void addUserWordUserDoesntExistTest() {

    }

    @Test
    public void addUserWordWordDoesntExistTest() {

    }
}
