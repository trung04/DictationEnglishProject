package com.project.englishweb.Repository;

import com.project.englishweb.Entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByAnswerContainingIgnoreCaseAndLesson_LessonId(String answer, Long lessonId, Pageable pageable);
    Page<Question> findByAnswerContainingIgnoreCase(String answer, Pageable pageable);
    Page<Question> findByLesson_LessonId(Long lessonId, Pageable pageable);
}