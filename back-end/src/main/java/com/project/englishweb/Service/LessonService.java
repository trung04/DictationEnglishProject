    package com.project.englishweb.Service;

    import com.project.englishweb.Entity.Lesson;
    import com.project.englishweb.DTO.LessonDTO;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;

    import java.util.List;

    public interface LessonService {
        Lesson createLesson(LessonDTO lessonDTO);
        List<Lesson> getAllLessons();
        Page<Lesson> getAllLessons(Pageable pageable);
        Lesson getLessonById(Long id);
        Lesson updateLesson(Long id, LessonDTO lessonDTO);
        void deleteLesson(Long id);
        List<Lesson> getLessonsByTitle(String title);
        List<Lesson> getLessonsByTopicId(Long topicId);
        Page<Lesson> searchLessons(String title, String levelName, String topicTitle, Pageable pageable);
    }

