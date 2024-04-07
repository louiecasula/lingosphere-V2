package com.passion.lingosphere.repositories;

import com.passion.lingosphere.models.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;

@Repository
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    List<UserWord> findByUser_UserId(Long userId);

    List<UserWord> findByDateSent(LocalDate LocalDate);
}
