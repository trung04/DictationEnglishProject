package com.project.englishweb.Service;

import com.project.englishweb.DTO.QuestionDTO;
import com.project.englishweb.DTO.QuestionResponseDTO;
import com.project.englishweb.Entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<QuestionResponseDTO> getAllQuestions();
    Optional<Question> getQuestionById(Long id);
    Question createQuestion(QuestionDTO questionDTO);
    Question updateQuestion(Long id, QuestionDTO questionDTO);
    void deleteQuestion(Long id);
    QuestionResponseDTO getQuestionResponseDTO(Question question);
    Page<QuestionResponseDTO> searchQuestions(String answer, Long lessonId, Pageable pageable);

}