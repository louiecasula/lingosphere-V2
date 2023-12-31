package com.passion.lingosphere.repositories;

import com.passion.lingosphere.models.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
    boolean existsByUserIdAndLanguageId(Long userId, Long languageId);

    List<UserLanguage> findByUserId(Long userId);
}
