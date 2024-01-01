package com.passion.lingosphere.controllers;

import com.passion.lingosphere.dtos.UserLanguageDto;
import com.passion.lingosphere.models.UserLanguage;
import com.passion.lingosphere.models.Word;
import com.passion.lingosphere.services.UserLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/language_preferences")
public class UserLanguageController {

    private final UserLanguageService userLanguageService;

    @Autowired
    public UserLanguageController(UserLanguageService userLanguageService) {
        this.userLanguageService = userLanguageService;
    }

    @PostMapping
    public ResponseEntity<?> addUserLanguage(@RequestBody UserLanguageDto userLanguageDto) {
        try {
            UserLanguage newUserLanguage = userLanguageService.addUserLanguage(userLanguageDto);
            return new ResponseEntity<>(newUserLanguage, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("User language creation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
