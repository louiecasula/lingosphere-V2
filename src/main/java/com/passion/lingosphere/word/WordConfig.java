package com.passion.lingosphere.word;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class WordConfig {

    @Bean
    CommandLineRunner commandLineRunner(WordRepository repository){
        return args -> {
            Word alacrity = new Word(
                    "English",
                    "alacrity",
                    "noun",
                    "/uh-lah-cruh-tee/",
                    "alacrity.mp3",
                    "liveliness, briskness",
                    "We accepted the invitation with alacrity",
                    "from Latin alacritatem"
            );

            repository.saveAll(
                    List.of(alacrity)
            );
        };
    }
}
