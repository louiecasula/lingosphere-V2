package com.passion.lingosphere.services;

import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.UserWord;
import com.passion.lingosphere.dtos.WordDto;
import com.passion.lingosphere.models.UserLanguage;
import com.passion.lingosphere.models.Word;
import com.passion.lingosphere.repositories.UserRepository;
import com.passion.lingosphere.repositories.UserWordRepository;
import com.passion.lingosphere.repositories.WordRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final UserLanguageService userLanguageService;
    private final UserWordRepository userWordRepository;

     @Autowired
    public WordService(WordRepository wordRepository, UserRepository userRepository, UserLanguageService userLanguageService, UserWordRepository userWordRepository) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
        this.userLanguageService = userLanguageService;
        this.userWordRepository = userWordRepository;
    }

    public Word addWord(WordDto wordDto) throws Exception {
         // Check if word already exists
        if (wordRepository.existsByText(wordDto.getText())) {
            throw new Exception("Word already exists");
        }

        // Create new Word entity from the DTO
        Word newWord = new Word();
        newWord.setText(wordDto.getText());
        newWord.setPartOfSpeech(wordDto.getPartOfSpeech());
        newWord.setDefinition(wordDto.getDefinition());
        newWord.setLanguage(wordDto.getLanguage());

        // Save the new word entity
        return wordRepository.save(newWord);
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Word getWordById(Long wordId) throws Exception {
         return wordRepository.findById(wordId)
                 .orElseThrow(() -> new Exception("Word doesn't exist"));
    }

    // TODO: finish this method. It's crucial to the overall functionality.
    public HashMap<String, Word> getWordsOfTheDay(Long userId) {
         // Get user's language preferences
        HashMap<String, Word> wordsOfTheDay = new HashMap<>();
        List<UserLanguage> userLanguages = userLanguageService.getUserLanguages(userId);

        // Select a word for each language
        for (UserLanguage userLanguage : userLanguages) {
            List<Word> eligibleWords = wordRepository.findWordsByLanguageAndLevel(userLanguage.getLanguage(), userLanguage.getProficiencyLevel());
            List<Word> unusedWords = filterUsedWords(eligibleWords, userId);

            // Archive each word in UserWord
            if (!unusedWords.isEmpty()) {
                Word wordOfTheDay = selectRandomWord(unusedWords);
                wordsOfTheDay.put(userLanguage.getLanguage().getCode(), wordOfTheDay);
                recordWordForUser(userId, wordOfTheDay);
            }
        }

        // Return the word(s)
        return wordsOfTheDay;
    }

    private List<Word> filterUsedWords(List<Word> words, Long userId) {
        // Fetch words already assigned to the user
        List<UserWord> userWords = userWordRepository.findByUserId(userId);

        // Convert to a set of word IDs for efficient lookup
        Set<Long> userWordIds = new HashSet<>();
        for (UserWord userWord: userWords) {
            userWordIds.add(userWord.getWord().getWordId());
        }
        // Filter out words that are in the used words set
        words.removeIf(word -> userWordIds.contains(word.getWordId()));
        return words;
    }

    private Word selectRandomWord(List<Word> words) {
         Random random = new Random();
         return words.get(random.nextInt(words.size()));
    }

    private void recordWordForUser(Long userId, Word word) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
         UserWord userWord = new UserWord(user, word, new Date());
         userWordRepository.save(userWord);
    }

}
