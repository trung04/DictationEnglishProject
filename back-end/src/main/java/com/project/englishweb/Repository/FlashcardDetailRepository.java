package com.project.englishweb.repository;

import com.project.englishweb.Entity.FlashcardDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FlashcardDetailRepository extends JpaRepository<FlashcardDetail, Long> {
    List<FlashcardDetail> findByFlashcardId(Long flashcardId);
}
