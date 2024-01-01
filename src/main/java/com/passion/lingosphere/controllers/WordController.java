package com.passion.lingosphere.controllers;

import com.passion.lingosphere.dtos.WordDto;
import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.models.Word;
import com.passion.lingosphere.services.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping(path = "/words")
public class WordController {

    private final WordService wordService;

    @Autowired
    public WordController(WordService wordService) {
        this.wordService = wordService;
    }

    @PostMapping
    public ResponseEntity<?> addWord(@RequestBody WordDto wordDto) {
        try {
            Word newWord = wordService.addWord(wordDto);
            return new ResponseEntity<>(newWord, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>("Word creation failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllWords() {
        List<Word> wordList = wordService.getAllWords();
        return new ResponseEntity<>(wordList, HttpStatus.OK);
    }
}
