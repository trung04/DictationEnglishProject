package com.project.englishweb.repository;

import com.project.englishweb.Entity.Flashcard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlashcardRepository  extends JpaRepository<Flashcard, Long> {
    @Query("SELECT f FROM Flashcard f WHERE f.user.userId = :userId")
    List<Flashcard> findByUserId(Long userId);
}
