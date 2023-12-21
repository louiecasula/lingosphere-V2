package com.passion.lingosphere.word;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    public List<Word> getWords() {
        return List.of(
                new Word(
                        1L,
                        "English",
                        "alacrity",
                        "noun",
                        "/uh-lah-cruh-tee/",
                        "alacrity.mp3",
                        "liveliness, briskness",
                        "We accepted the invitation with alacrity",
                        "from Latin alacritatem"
                )
        );
    }
}
