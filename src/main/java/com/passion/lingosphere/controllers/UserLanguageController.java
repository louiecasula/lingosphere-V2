package com.passion.lingosphere.controllers;

import com.passion.lingosphere.dtos.UserLanguageDto;
import com.passion.lingosphere.models.UserLanguage;
import com.passion.lingosphere.services.UserLanguageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/languages")
public class UserLanguageController {

    private final UserLanguageService userLanguageService;

    @Autowired
    public UserLanguageController(UserLanguageService userLanguageService) {
        this.userLanguageService = userLanguageService;
    }

    @PostMapping
    public ResponseEntity<?> setLanguagePreferences(@PathVariable Long userId, @RequestBody List<UserLanguageDto> preferences) {
        userLanguageService.setLanguagePreferences(userId, preferences);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
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

    @GetMapping
    public ResponseEntity<?> getUserLanguages(@PathVariable Long userId) {
        List<UserLanguage> userLanguageList = userLanguageService.getUserLanguages(userId);
        return new ResponseEntity<>(userLanguageList, HttpStatus.OK);
    }

    @PutMapping("/{languageId}")
    public ResponseEntity<?> updateUserLanguage(@PathVariable Long userId, @PathVariable Long languageId, @RequestBody UserLanguageDto userLanguageDto) {
        try {
            UserLanguage updatedUserLanguage = userLanguageService.updateUserLanguage(userId, languageId, userLanguageDto);
            return ResponseEntity.ok(updatedUserLanguage);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<?> removeUserLanguage(@PathVariable Long userId, @PathVariable Long languageId) {
        try {
            userLanguageService.removeUserLanguage(userId, languageId);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
