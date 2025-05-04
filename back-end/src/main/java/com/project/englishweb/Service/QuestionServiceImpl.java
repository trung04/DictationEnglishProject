package com.project.englishweb.Service;

import com.project.englishweb.DTO.QuestionDTO;
import com.project.englishweb.DTO.QuestionResponseDTO;
import com.project.englishweb.Entity.Question;
import com.project.englishweb.Mapper.QuestionMapper;
import com.project.englishweb.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private LessonService lessonService;

    @Override
    public List<QuestionResponseDTO> getAllQuestions() {
        return questionRepository.findAll().stream()
                .map(questionMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public Question createQuestion(QuestionDTO questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        question.setLesson(lessonService.getLessonById(questionDTO.getLessonId()));
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestion(Long id, QuestionDTO questionDTO) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        
        existingQuestion.setUrl(questionDTO.getUrl());
        existingQuestion.setAnswer(questionDTO.getAnswer());
        existingQuestion.setLesson(lessonService.getLessonById(questionDTO.getLessonId()));
        
        return questionRepository.save(existingQuestion);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    @Override
    public QuestionResponseDTO getQuestionResponseDTO(Question question) {
        return questionMapper.toResponseDTO(question);
    }

    @Override
    public Page<QuestionResponseDTO> searchQuestions(String answer, Long lessonId, Pageable pageable) {
        Page<Question> page;
        if (answer != null && !answer.isEmpty() && lessonId != null) {
            page = questionRepository.findByAnswerContainingIgnoreCaseAndLesson_LessonId(answer, lessonId, pageable);
        } else if (answer != null && !answer.isEmpty()) {
            page = questionRepository.findByAnswerContainingIgnoreCase(answer, pageable);
        } else if (lessonId != null) {
            page = questionRepository.findByLesson_LessonId(lessonId, pageable);
        } else {
            page = questionRepository.findAll(pageable);
        }
        return page.map(questionMapper::toResponseDTO);
    }
} 