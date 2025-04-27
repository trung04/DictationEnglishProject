package com.project.englishweb.Controller;

import com.project.englishweb.Entity.Question;
import com.project.englishweb.Service.QuestionService;
import com.project.englishweb.DTO.QuestionDTO;
import com.project.englishweb.DTO.QuestionResponseDTO;
import com.project.englishweb.Service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;
    private final LessonService lessonService;

    @Autowired
    public QuestionController(QuestionService questionService, LessonService lessonService) {
        this.questionService = questionService;
        this.lessonService = lessonService;
    }

    @GetMapping
    public String questionManagement(@RequestParam(required = false) String answer,
                                     @RequestParam(required = false) Long lessonId,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(required = false) Long editId,
                                     Model model) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<QuestionResponseDTO> questionsPage = questionService.searchQuestions(answer, lessonId, pageable);

        model.addAttribute("questions", questionsPage.getContent());
        model.addAttribute("lessons", lessonService.getAllLessons());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", questionsPage.getTotalPages());
        model.addAttribute("activePage", "questions");
        model.addAttribute("questionDTO", new QuestionDTO());
        model.addAttribute("editQuestionDTO", new QuestionDTO());
        model.addAttribute("editQuestionId", editId);
        model.addAttribute("searchAnswer", answer);
        model.addAttribute("searchLessonId", lessonId);

        if (editId != null) {
            Optional<Question> questionToEdit = questionService.getQuestionById(editId);
            if (questionToEdit.isPresent()) {
                Question q = questionToEdit.get();
                QuestionDTO editDTO = new QuestionDTO(q.getQuestionId(), q.getUrl(), q.getAnswer(), q.getLesson().getLessonId());
                model.addAttribute("editQuestionDTO", editDTO);
            }
        }
        return "questions/management";
    }

    @PostMapping
    public String createQuestion(@ModelAttribute QuestionDTO questionDTO) {
        questionService.createQuestion(questionDTO);
        return "redirect:/questions";
    }

    @PostMapping("/update/{id}")
    public String updateQuestion(@PathVariable Long id, @ModelAttribute QuestionDTO questionDTO) {
        questionService.updateQuestion(id, questionDTO);
        return "redirect:/questions";
    }

    @GetMapping("/delete/{id}")
    public String deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return "redirect:/questions";
    }
}