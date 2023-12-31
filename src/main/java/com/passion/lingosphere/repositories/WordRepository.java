package com.passion.lingosphere.repositories;

import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    boolean existsByText(String text);

    List<Word> findWordsByLanguageAndLevel(Language language, Integer level);
}
