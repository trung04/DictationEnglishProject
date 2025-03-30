package com.project.englishweb.Controller;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.DTO.LessonDTO;
import com.project.englishweb.Service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<Lesson> createLesson(@RequestBody LessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.createLesson(lessonDTO));
    }

    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        return ResponseEntity.ok(lessonService.getAllLessons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody LessonDTO lessonDTO) {
        return ResponseEntity.ok(lessonService.updateLesson(id, lessonDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<Lesson>> getLessonsByTopicId(@PathVariable Long topicId) {
        return ResponseEntity.ok(lessonService.getLessonsByTopicId(topicId));
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchLessons(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "level", required = false) String level) {
        List<Lesson> lessons;
        // Xử lý tìm kiếm dựa trên tiêu chí
        if (title != null && level != null) {
            lessons = lessonService.getLessonsByTitleAndLevel(title, level);
        } else if (title != null) {
            lessons = lessonService.getLessonsByTitle(title);
        } else if (level != null) {
            lessons = lessonService.getLessonsByLevel(level);
        } else {
            lessons = lessonService.getAllLessons();
        }
        if (lessons.isEmpty()) {
            return ResponseEntity.status(404).body("No lessons found");
        }
        return ResponseEntity.ok(lessons);
    }
}