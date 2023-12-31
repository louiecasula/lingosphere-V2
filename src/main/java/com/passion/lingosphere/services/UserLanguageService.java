package com.passion.lingosphere.services;

import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.dtos.UserLanguageDto;
import com.passion.lingosphere.models.UserLanguage;
import com.passion.lingosphere.repositories.LanguageRepository;
import com.passion.lingosphere.repositories.UserLanguageRepository;
import com.passion.lingosphere.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserLanguageService {

    private final UserLanguageRepository userLanguageRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;

    @Autowired
    public UserLanguageService(UserLanguageRepository userLanguageRepository, UserRepository userRepository, LanguageRepository languageRepository) {
        this.userLanguageRepository = userLanguageRepository;
        this.userRepository = userRepository;
        this.languageRepository = languageRepository;
    }

    public UserLanguage addUserLanguage(UserLanguageDto userLanguageDto) throws EntityNotFoundException, DataIntegrityViolationException {
        // Check if user or language don't exist
        User user = userRepository.findById(userLanguageDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userLanguageDto.getUserId()));
        Language language = languageRepository.findById(userLanguageDto.getLanguageId())
                .orElseThrow(() -> new EntityNotFoundException("Language not found with ID: " + userLanguageDto.getLanguageId()));

        // Check if language is already in the user's UserLanguageRepository
        if (userLanguageRepository.existsByUserIdAndLanguageId(userLanguageDto.getUserId(), userLanguageDto.getLanguageId())) {
            throw new DataIntegrityViolationException("Duplicate language entry");
        }
        // TODO: Exception for invalid proficiencyLevel?

        // Create new UserLanguage entity from the DTO
        UserLanguage newUserLanguage = new UserLanguage();
        newUserLanguage.setUser(user);
        newUserLanguage.setLanguage(language);
        newUserLanguage.setProficiencyLevel(userLanguageDto.getProficiencyLevel());

        // Save the new user language entity
        return userLanguageRepository.save(newUserLanguage);
    }
}
