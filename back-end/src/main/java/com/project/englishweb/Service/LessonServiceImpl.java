package com.project.englishweb.Service;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Level;
import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Repository.LessonRepository;
import com.project.englishweb.Repository.LevelRepository;
import com.project.englishweb.Repository.TopicRepository;
import com.project.englishweb.DTO.LessonDTO;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
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

        Level level = levelRepository.findById(lessonDTO.getLevelId())
                .orElseThrow(() -> new NoSuchElementException("Level not found"));

        String[] lines = lessonDTO.getTranscript().split("\n");
        JSONArray transcriptArray = new JSONArray();

        String start = "0:00";
        String end = "";
        int id = 1;
        int questionCount = 0;

        for (String line : lines) {
            String[] parts = line.trim().split(" ", 2);
            if (parts.length < 2) {
                if (parts[0].charAt(parts[0].length() - 3) == ':') {
                    questionCount++;
                    end = parts[0].trim();
                    continue;
                }
            }

            JSONObject entry = new JSONObject();
            entry.put("id", id);
            entry.put("text", line.trim());
            entry.put("start", start);
            entry.put("end", end);

            transcriptArray.put(entry);

            start = end;
            id++;
        }

        String transcript = transcriptArray.toString(2);

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);
        lesson.setLevelName(level.getName());
        lesson.setTranscript(transcript);
        lesson.setQuestionCount(questionCount);

        return lessonRepository.save(lesson);
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

        String[] lines = lessonDTO.getTranscript().split("\n");
        JSONArray transcriptArray = new JSONArray();

        String start = "0:00";
        String end = "";
        int ID = 1;
        int questionCount = 0;

        for (String line : lines) {
            String[] parts = line.trim().split(" ", 2);
            if (parts.length < 2) {
                if (parts[0].charAt(parts[0].length() - 3) == ':') {
                    questionCount++;
                    end = parts[0].trim();
                    continue;
                }
            }

            JSONObject entry = new JSONObject();
            entry.put("id", ID);
            entry.put("text", line.trim());
            entry.put("start", start);
            entry.put("end", end);

            transcriptArray.put(entry);

            start = end;
            ID++;
        }

        String transcript = transcriptArray.toString(2);
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);
        lesson.setLevelName(level.getName());
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);
        lesson.setTranscript(transcript);
        lesson.setQuestionCount(questionCount);

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
