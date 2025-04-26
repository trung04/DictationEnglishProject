package com.project.englishweb.Controller.API;

import com.project.englishweb.Entity.Question;
import com.project.englishweb.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@RestController
@RequestMapping("/api/questions")
@CrossOrigin(origins = "http://localhost:3000") // Cho phép React truy cập
public class QuestionApiController {

    private final QuestionService questionService;

    @Autowired
    public QuestionApiController(QuestionService questionService) {
        this.questionService = questionService;
    }
    // Lấy tất cả questions
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.findAll();
        return ResponseEntity.ok(questions);
    }
}
























































    // // Lấy question theo ID
    // @GetMapping("/{id}")
    // public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
    //     Optional<Question> question = questionService.findById(id);
    //     return question.map(ResponseEntity::ok)
    //             .orElse(ResponseEntity.notFound().build());
    // }

    // // Tạo mới question
    // @PostMapping
    // public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
    //     Question savedQuestion = questionService.save(question);
    //     return ResponseEntity.ok(savedQuestion);
    // }

    // // Cập nhật question
    // @PutMapping("/{id}")
    // public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
    //     if (!questionService.findById(id).isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     question.setId(id);
    //     Question updatedQuestion = questionService.save(question);
    //     return ResponseEntity.ok(updatedQuestion);
    // }

    // // Xóa question
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    //     if (!questionService.findById(id).isPresent()) {
    //         return ResponseEntity.notFound().build();
    //     }
    //     questionService.deleteById(id);
    //     return ResponseEntity.ok().build();
    // }
