package com.project.englishweb.Service;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Level;
import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Repository.LessonRepository;
import com.project.englishweb.Repository.LevelRepository;
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
    private final LevelRepository levelRepository;
    private final TopicRepository topicRepository;

    public Lesson createLesson(LessonDTO lessonDTO) {
        Topic topic = topicRepository.findById(lessonDTO.getTopicId())
                .orElseThrow(() -> new NoSuchElementException("Topic not found"));

        // Tìm Level từ levelId
        Level level = levelRepository.findById(lessonDTO.getLevelId())
                .orElseThrow(() -> new NoSuchElementException("Level not found"));

        // Tạo bài học mới
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);  // Gán level từ DTO
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);

        lesson.setLevelName(level.getName());

        return lessonRepository.save(lesson);  // Lưu bài học vào cơ sở dữ liệu
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    @Override
    public Page<Lesson> getAllLessons(Pageable pageable) {
        return lessonRepository.findAll(pageable);
    }

    @Override
    public Lesson getLessonById(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lesson không tồn tại"));
    }

    @Override
    public Lesson updateLesson(Long id, LessonDTO lessonDTO) {
        Lesson lesson = getLessonById(id);

        Level level = levelRepository.findById(lessonDTO.getLevelId())
                .orElseThrow(() -> new NoSuchElementException("Level không tồn tại"));

        Topic topic = topicRepository.findById(lessonDTO.getTopicId())
                .orElseThrow(() -> new NoSuchElementException("Topic không tồn tại"));

        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);
        lesson.setLevelName(level.getName());
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);

        return lessonRepository.save(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }

    @Override
    public List<Lesson> getLessonsByTitle(String title) {
        return lessonRepository.findByTitle(title);
    }
    @Override
    public List<Lesson> getLessonsByTopicId(Long topicId) {
        return lessonRepository.findByTopicTopicId(topicId);
    }
    @Override
    public Page<Lesson> searchLessons(String title, String levelName, String topicTitle, Pageable pageable) {
        return lessonRepository.findByTitleAndLevelNameAndTopicTitleWithSearch(title, levelName, topicTitle, pageable);
    }
}
