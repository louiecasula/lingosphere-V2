package com.passion.lingosphere.services;

import com.passion.lingosphere.dtos.UserLanguageDto;
import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.UserLanguage;
import com.passion.lingosphere.repositories.LanguageRepository;
import com.passion.lingosphere.repositories.UserLanguageRepository;
import com.passion.lingosphere.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UserLanguageServiceTest {

    @Mock
    private UserLanguageRepository userLanguageRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private LanguageRepository languageRepository;
    @Mock
    private UserLanguage userLanguage;
    @Mock
    private User user;
    @Mock
    private Language language;
    @InjectMocks
    private UserLanguageService userLanguageService;
    private UserLanguageDto userLanguageDto;

    @BeforeEach
    public void setup() {
        Long userId = 0L;
        Long languageId = 0L;
        Integer proficiencyLevel = 1;
        userLanguageDto = new UserLanguageDto(userId, languageId, proficiencyLevel);
    }

    @Test
    public void addUserLanguageSuccessfulTest() {
        given(userRepository.findById(userLanguageDto.getUserId())).willReturn(Optional.of(user));
        given(languageRepository.findById(userLanguageDto.getLanguageId())).willReturn(Optional.of(language));
        given(userLanguageRepository.existsByUserIdAndLanguageId(userLanguageDto.getUserId(), userLanguageDto.getLanguageId())).willReturn(false);
        given(userLanguageRepository.save(any(UserLanguage.class))).willReturn(new UserLanguage(user, language, userLanguageDto.getProficiencyLevel()));

        UserLanguage added = userLanguageService.addUserLanguage(userLanguageDto);

        assertNotNull(added);
        assertEquals(user, added.getUser());
        assertEquals(language, added.getLanguage());
        assertEquals(userLanguageDto.getProficiencyLevel(), added.getProficiencyLevel());
    }

    @Test
    public void addUserLanguageUserDoesntExistTest() {
        given(userRepository.findById(userLanguageDto.getUserId())).willReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userLanguageService.addUserLanguage(userLanguageDto));
    }

    @Test
    public void addUserLanguageLanguageDoesntExistTest() {
        lenient().when(languageRepository.existsById(userLanguageDto.getLanguageId())).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> userLanguageService.addUserLanguage(userLanguageDto));
    }

    // TODO: Fix this test...
    @Test
    public void addUserLanguageDuplicateLanguageTest() {
//        given(userRepository.existsById(userLanguageDto.getUserId())).willReturn(true);
//        given(languageRepository.existsById(userLanguageDto.getLanguageId())).willReturn(true);
//        given(userLanguageRepository.existsByUserIdAndLanguageId(userLanguageDto.getUserId(), userLanguageDto.getLanguageId())).willReturn(true);
//
//        assertThrows(DataIntegrityViolationException.class, () -> userLanguageService.addUserLanguage(userLanguageDto));
    }

    @Test
    public void getUserLanguagesTest() {
        User user = new User();
        Language language1 = new Language();
        Language language2 = new Language();
        UserLanguage userLanguage1 = new UserLanguage(user, language1, 3);
        UserLanguage userLanguage2 = new UserLanguage(user, language2, 2);
        List<UserLanguage> expected = Arrays.asList(userLanguage1, userLanguage2);
        given(userLanguageRepository.findByUserId(user.getUserId())).willReturn(expected);

        List<UserLanguage> actual = userLanguageService.getUserLanguages(user.getUserId());

        assertEquals(expected, actual);
    }

}
