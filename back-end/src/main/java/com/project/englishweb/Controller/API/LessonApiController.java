package com.project.englishweb.Controller.API;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Service.LessonServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/lessons")
@CrossOrigin(origins = "http://localhost:3000")

public class LessonApiController {
    private LessonServiceImpl lessonService;
    public LessonApiController(LessonServiceImpl lessonService) {
        this.lessonService = lessonService;
    }
    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }
    @GetMapping("topic/{topicId}")
    public List<Lesson> getLessonByTopicId(@PathVariable Long topicId) {
        return lessonService.getLessonsByTopicId(topicId);
    }
    @GetMapping("{lessonId}")
    public Lesson getLessonById(@PathVariable Long lessonId) {
        return lessonService.getLessonById(lessonId);
    }

}
