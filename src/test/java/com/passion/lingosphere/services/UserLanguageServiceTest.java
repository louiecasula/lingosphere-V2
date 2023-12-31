package com.passion.lingosphere.services;

import com.passion.lingosphere.dtos.UserLanguageDto;
import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.UserLanguage;
import com.passion.lingosphere.repositories.LanguageRepository;
import com.passion.lingosphere.repositories.UserLanguageRepository;
import com.passion.lingosphere.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
        given(userLanguageRepository.existsByLanguage_Id(userLanguageDto.getLanguageId())).willReturn(false);
        given(userLanguageRepository.save(any(UserLanguage.class))).willReturn(new UserLanguage(user, language, userLanguageDto.getProficiencyLevel()));

        UserLanguage added = userLanguageService.addUserLanguage(userLanguageDto);

        assertNotNull(added);
        assertEquals(user, added.getUser());
        assertEquals(language, added.getLanguage());
        assertEquals(userLanguageDto.getProficiencyLevel(), added.getProficiencyLevel());
    }

    @Test
    public void addUserLanguageUserDoesntExistTest() {

    }

    @Test
    public void addUserLanguageLanguageDoesntExistTest() {

    }

    @Test
    public void addUserLanguageDuplicateLanguageTest() {

    }
}
