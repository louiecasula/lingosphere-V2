package com.passion.lingosphere.repositories;

import com.passion.lingosphere.models.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
    boolean existsByName(String name);

    boolean existsByCode(String code);
}
