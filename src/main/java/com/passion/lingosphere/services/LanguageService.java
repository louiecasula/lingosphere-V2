package com.passion.lingosphere.services;

import com.passion.lingosphere.dtos.LanguageDto;
import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.repositories.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    @Autowired
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public Language addLanguage(LanguageDto languageDto) throws Exception {
        // Check if language already exists
        if (languageRepository.existsByName(languageDto.getName())) {
            throw new Exception("Language already exists");
        }
        if (languageRepository.existsByCode(languageDto.getCode())) {
            throw new Exception("Language code already exists");
        }

        // Create new Language entity from the DTO
        Language newLanguage = new Language();
        newLanguage.setName(languageDto.getName());
        newLanguage.setCode(languageDto.getCode());

        // Save the new language entity
        return languageRepository.save(newLanguage);
    }

    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }
}
