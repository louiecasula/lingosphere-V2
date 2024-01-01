package com.passion.lingosphere.services;

import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.UserWord;
import com.passion.lingosphere.models.Word;
import com.passion.lingosphere.repositories.UserRepository;
import com.passion.lingosphere.repositories.UserWordRepository;
import com.passion.lingosphere.repositories.WordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserWordService {

    private final UserWordRepository userWordRepository;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    @Autowired
    public UserWordService(UserWordRepository userWordRepository, UserRepository userRepository, WordRepository wordRepository) {
        this.userWordRepository = userWordRepository;
        this.userRepository = userRepository;
        this.wordRepository = wordRepository;
    }

    public UserWord addUserWord(Long userId, Long wordId) {
        // Check if user or word don't exist
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
        Word word = wordRepository.findById(wordId)
                .orElseThrow(() -> new EntityNotFoundException("Word not found with ID: " + wordId));

        // Create new UserWord entity
        UserWord newUserWord = new UserWord(user, word, new Date());

        // Save the new user word entity
        return userWordRepository.save(newUserWord);
    }

    public List<UserWord> getUserWords(Long userId) {
        return userWordRepository.findByUser_UserId(userId);
    }
}
