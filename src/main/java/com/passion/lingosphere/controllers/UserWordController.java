package com.passion.lingosphere.controllers;

import com.passion.lingosphere.models.UserWord;
import com.passion.lingosphere.services.UserWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/words")
public class UserWordController {

    private final UserWordService userWordService;

    @Autowired
    public UserWordController(UserWordService userWordService) {
        this.userWordService = userWordService;
    }

    @PostMapping("/{userId}/words/{wordId}")
    public ResponseEntity<?> addUserWord(@PathVariable Long userId, @PathVariable Long wordId) {
        try {
            UserWord newUserWord = userWordService.addUserWord(userId, wordId);
            return new ResponseEntity<>(newUserWord, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("User word creation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping
    public ResponseEntity<?> getUserWords(@PathVariable Long userId) {
        List<UserWord> userWordList = userWordService.getUserWords(userId);
        return new ResponseEntity<>(userWordList, HttpStatus.OK);
    }
}
