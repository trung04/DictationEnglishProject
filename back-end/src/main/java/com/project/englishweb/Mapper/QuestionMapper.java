package com.project.englishweb.Mapper;

import com.project.englishweb.DTO.QuestionDTO;
import com.project.englishweb.DTO.QuestionResponseDTO;
import com.project.englishweb.Entity.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public QuestionDTO toDTO(Question question) {
        QuestionDTO dto = new QuestionDTO();
        dto.setQuestionId(question.getQuestionId());
        dto.setUrl(question.getUrl());
        dto.setAnswer(question.getAnswer());
        dto.setLessonId(question.getLesson() != null ? question.getLesson().getLessonId() : null);
        return dto;
    }

    public QuestionResponseDTO toResponseDTO(Question question) {
        QuestionResponseDTO dto = new QuestionResponseDTO();
        dto.setQuestionId(question.getQuestionId());
        dto.setUrl(question.getUrl());
        dto.setAnswer(question.getAnswer());
        dto.setLessonName(
            question.getLesson() != null && question.getLesson().getTitle() != null
                ? question.getLesson().getTitle()
                : null
        );
        return dto;
    }

    public Question toEntity(QuestionDTO dto) {
        Question question = new Question();
        question.setUrl(dto.getUrl());
        question.setAnswer(dto.getAnswer());
        // lesson sẽ được set ở service
        return question;
    }
} 