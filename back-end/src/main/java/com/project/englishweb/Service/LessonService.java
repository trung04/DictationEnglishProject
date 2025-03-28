package com.project.englishweb.Service;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.DTO.LessonDTO;

import java.util.List;

public interface LessonService {
    Lesson createLesson(LessonDTO lessonDTO);
    List<Lesson> getAllLessons();
    Lesson getLessonById(Long id);
    Lesson updateLesson(Long id, LessonDTO lessonDTO);
    void deleteLesson(Long id);
    List<Lesson> getLessonsByTopicId(Long topicId);
    List<Lesson> getLessonsByLevel(String level);
    List<Lesson> getLessonsByTitle(String title);
    List<Lesson> getLessonsByTitleAndLevel(String title, String level);
}