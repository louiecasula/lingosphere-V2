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
import org.springframework.cglib.core.Local;

import java.util.Arrays;
import java.time.LocalDate;
import java.util.List;
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
        given(userWordRepository.save(any(UserWord.class))).willReturn(new UserWord(user, word, LocalDate.now()));

        UserWord added = userWordService.addUserWord(0L, 0L);

        assertNotNull(added);
        assertEquals(user, added.getUser());
        assertEquals(word, added.getWord());
    }

    // TODO: Finish these tests!
    @Test
    public void addUserWordUserDoesntExistTest() {

    }

    @Test
    public void addUserWordWordDoesntExistTest() {

    }

    @Test
    public void getUserWordsTest() {
        User user = new User();
        Word word1 = new Word();
        Word word2 = new Word();
        UserWord userWord1 = new UserWord(user, word1, LocalDate.now());
        UserWord userWord2 = new UserWord(user, word2, LocalDate.now());
        List<UserWord> expected = Arrays.asList(userWord1, userWord2);
        given(userWordRepository.findByUser_UserId(user.getUserId())).willReturn(expected);

        List<UserWord> actual = userWordService.getUserWords(user.getUserId());

        assertEquals(expected, actual);
    }
}
