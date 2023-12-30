package com.passion.lingosphere.repositories;

import com.passion.lingosphere.models.UserWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
}
