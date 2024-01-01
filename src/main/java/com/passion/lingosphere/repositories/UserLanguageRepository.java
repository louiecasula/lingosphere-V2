package com.passion.lingosphere.repositories;

import com.passion.lingosphere.models.Language;
import com.passion.lingosphere.models.User;
import com.passion.lingosphere.models.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, Long> {
    boolean existsByUser_UserIdAndLanguage_LanguageId(Long userId, Long languageId);

    List<UserLanguage> findByUser_UserId(Long userId);

    Optional<UserLanguage> findByUserAndLanguage(User user, Language language);
}
