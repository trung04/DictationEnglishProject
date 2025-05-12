package com.project.englishweb.repository;

import com.project.englishweb.DTO.ProgressDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.project.englishweb.Entity.Progress;
import org.springframework.data.jpa.repository.Query;


import java.util.List;
import java.util.Optional;

    @Repository
    public interface ProgressRepository extends JpaRepository<Progress, Long> {
        @Query("SELECT p FROM Progress p WHERE p.user.userId = :userId AND p.lesson.lessonId = :lessonId")
        Optional<Progress> findByUserIdAndLessonId(@Param("userId") Long userId, @Param("lessonId") Long lessonId);
        @Query("SELECT new com.project.englishweb.DTO.ProgressDTO(p.lessonStatus, p.attempts, p.user.userId, p.lesson.lessonId,p.lesson) " +
                "FROM Progress p WHERE p.user.userId = :userId AND p.lessonStatus = 0")
        List<ProgressDTO> findByUserId(@Param("userId") Long userId);
    }
