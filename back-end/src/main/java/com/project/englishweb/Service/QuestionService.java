package com.project.englishweb.Service;

import com.project.englishweb.Entity.Question;
import com.project.englishweb.Repository.QuestionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    // Lấy tất cả câu hỏi
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // Lấy câu hỏi theo ID
    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    // Thêm mới câu hỏi
    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    // Cập nhật câu hỏi
    public Question updateQuestion(Long id, Question questionDetails) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Câu hỏi không tồn tại"));
        question.setUrl(questionDetails.getUrl());
        question.setAnswer(questionDetails.getAnswer());
        question.setLesson(questionDetails.getLesson());
        return questionRepository.save(question);
    }

    // Xóa câu hỏi
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}