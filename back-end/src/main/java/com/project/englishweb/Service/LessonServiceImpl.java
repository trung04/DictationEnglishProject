package com.project.englishweb.Service;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Repository.LessonRepository;
import com.project.englishweb.Repository.TopicRepository;
import com.project.englishweb.DTO.LessonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final TopicRepository topicRepository;

    @Override
    public Lesson createLesson(LessonDTO lessonDTO) {
        Topic topic = topicRepository.findById(lessonDTO.getTopicId())
                .orElseThrow(() -> new NoSuchElementException("Topic not found"));

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(lessonDTO.getLevel());
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);

        return lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Page<Lesson> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable); // Sử dụng phương thức findAll có Pageable
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lesson not found"));
    }

    @Override
    public Lesson updateLesson(Long id, LessonDTO lessonDTO) {
        Lesson lesson = getLessonById(id);

        Topic topic = topicRepository.findById(lessonDTO.getTopicId())
                .orElseThrow(() -> new NoSuchElementException("Topic not found"));

        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(lessonDTO.getLevel());
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);

        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getLessonsByTopicId(Long topicId) {
        return lessonRepository.findByTopicTopicId(topicId);
    }
    @Override
    public List<Lesson> getLessonsByLevel(String level) {
        return lessonRepository.findByLevel(level);
    }
    @Override
    public List<Lesson> getLessonsByTitle(String title) {
        return lessonRepository.findByTitle(title);
    }
    @Override
    public Page<Lesson> searchLessons(String title, String level, Long topicId, Pageable pageable) {
        return lessonRepository.findByTitleAndLevelAndTopicIdWithSearch(title, level, topicId, pageable); // Gọi phương thức repository mới
    }
}