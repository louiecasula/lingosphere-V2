package com.passion.lingosphere.repositories;

import com.passion.lingosphere.models.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
    boolean existsByLanguage_Id(Long languageId);
}
