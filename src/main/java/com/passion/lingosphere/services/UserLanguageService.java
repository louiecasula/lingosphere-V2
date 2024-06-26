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
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void setLanguagePreferences(Long userId, List<UserLanguageDto> preferences) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

        for (UserLanguageDto userLanguageDto : preferences) {
            Language language = languageRepository.findById(userLanguageDto.getLanguageId())
                    .orElseThrow(() -> new EntityNotFoundException("Language not found with ID: " + userLanguageDto.getLanguageId()));
            UserLanguage userLanguage = new UserLanguage();
            userLanguage.setUser(user);
            userLanguage.setLanguage(language);
            userLanguage.setProficiencyLevel(userLanguageDto.getProficiencyLevel());

            userLanguageRepository.save(userLanguage);
        }
    }

    public UserLanguage addUserLanguage(UserLanguageDto userLanguageDto) throws EntityNotFoundException {
        // Check if user or language don't exist
        User user = userRepository.findById(userLanguageDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userLanguageDto.getUserId()));
        Language language = languageRepository.findById(userLanguageDto.getLanguageId())
                .orElseThrow(() -> new EntityNotFoundException("Language not found with ID: " + userLanguageDto.getLanguageId()));

        // Check if language is already in the user's UserLanguageRepository
        if (userLanguageRepository.existsByUser_UserIdAndLanguage_LanguageId(userLanguageDto.getUserId(), userLanguageDto.getLanguageId())) {
            throw new EntityNotFoundException("Duplicate language entry");
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

    public List<UserLanguage> getUserLanguages(Long userId) {
        return userLanguageRepository.findByUser_UserId(userId);
    }

    public UserLanguage updateUserLanguage(Long userId, Long languageId, UserLanguageDto userLanguageDto) {
        // Check if user and language exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with ID: " + languageId));

        // Check if language exists in UserLanguage repo
        UserLanguage userLanguage = userLanguageRepository.findByUserAndLanguage(user, language)
                .orElseThrow(() -> new EntityNotFoundException("User language preference not found"));

        // Update fields
        userLanguage.setProficiencyLevel(userLanguageDto.getProficiencyLevel());

        return userLanguageRepository.save(userLanguage);
    }

    public void removeUserLanguage(Long userId, Long languageId) {
        // Check if user and language exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new EntityNotFoundException("Language not found with ID: " + languageId));

        // Check if language exists in UserLanguage repo
        UserLanguage userLanguage = userLanguageRepository.findByUserAndLanguage(user, language)
                .orElseThrow(() -> new EntityNotFoundException("User language preference not found"));

        userLanguageRepository.delete(userLanguage);
    }
}
