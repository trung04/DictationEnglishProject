package com.project.englishweb.Service;

import com.project.englishweb.DTO.QuestionDTO;
import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Question;
import com.project.englishweb.Repository.LessonRepository;
import com.project.englishweb.Repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private LessonRepository lessonRepository;

    private QuestionDTO convertToDTO(Question question) {
        return new QuestionDTO(
                question.getQuestionId(),
                question.getUrl(),
                question.getAnswer(),
                question.getLesson().getLessonId()
        );
    }

    private Question convertToEntity(QuestionDTO dto) {
        Lesson lesson = lessonRepository.findById(dto.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + dto.getLessonId()));

        Question question = new Question();
        question.setQuestionId(dto.getQuestionId());
        question.setUrl(dto.getUrl());
        question.setAnswer(dto.getAnswer());
        question.setLesson(lesson);

        return question;
    }

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));
        return convertToDTO(question);
    }

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
        Question question = convertToEntity(questionDTO);
        Question saved = questionRepository.save(question);
        return convertToDTO(saved);
    }

    @Override
    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        Question existing = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found with id: " + id));

        existing.setUrl(questionDTO.getUrl());
        existing.setAnswer(questionDTO.getAnswer());

        Lesson lesson = lessonRepository.findById(questionDTO.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + questionDTO.getLessonId()));
        existing.setLesson(lesson);

        Question updated = questionRepository.save(existing);
        return convertToDTO(updated);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
