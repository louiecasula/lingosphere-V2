package com.passion.lingosphere.word;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/word")
public class WordController {

    @GetMapping
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
