package com.passion.lingosphere.services;

import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.dtos.WordDto;
import com.passion.lingosphere.models.Word;
import com.passion.lingosphere.repositories.WordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class WordServiceTest {

    @Mock
    private WordRepository wordRepository;
    @Mock
    private Word word;
    @InjectMocks
    private WordService wordService;
    private WordDto wordDto;

    @BeforeEach
    public void setup() {
        String text = "testword";
        String partOfSpeech = "test";
        String definition = "this is a test word";
        Language language = new Language("testlang", "tl");
        wordDto = new WordDto(text, partOfSpeech, definition, language);
    }

    @Test
    public void addWordSuccessfulTest() throws Exception {
        given(wordRepository.existsByText(wordDto.getText())).willReturn(false);
        given(wordRepository.save(any(Word.class))).willReturn(new Word(wordDto.getText(), wordDto.getPartOfSpeech(), wordDto.getDefinition(), wordDto.getLanguage()));

        Word added = wordService.addWord(wordDto);

        assertNotNull(added);
        assertEquals(wordDto.getText(), added.getText());
    }

    @Test
    public void addWordTextTakenTest() {
        given(wordRepository.existsByText(word.getText())).willReturn(true);

        assertThrows(Exception.class, () -> wordService.addWord(wordDto));
    }
}
