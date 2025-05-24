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

    private static class TranscriptResult {
        String transcriptJson;
        int questionCount;

        TranscriptResult(String transcriptJson, int questionCount) {
            this.transcriptJson = transcriptJson;
            this.questionCount = questionCount;
        }
    }

    private TranscriptResult parseTranscript(String rawTranscript) {
        String[] lines = rawTranscript.split("\n");
        JSONArray transcriptArray = new JSONArray();

        int id = 1;
        String start = "";
        String end = "";
        String text = "";
        int questionCount = 0;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i].trim();

            if (line.matches("^\\d{1,2}:\\d{2}$")) {
                if (!start.isEmpty() && !text.isEmpty()) {
                    end = line;

                    JSONObject entry = new JSONObject();
                    entry.put("id", id++);
                    entry.put("text", text);
                    entry.put("start", start);
                    entry.put("end", end);
                    transcriptArray.put(entry);

                    start = end;
                    text = "";
                    questionCount++;
                } else {
                    start = line;
                }
            } else {
                text = line;
            }
        }

        // Thêm đoạn cuối cùng nếu chưa có end
        if (!start.isEmpty() && !text.isEmpty()) {
            JSONObject entry = new JSONObject();
            entry.put("id", id);
            entry.put("text", text);
            entry.put("start", start);
            entry.put("end", "");
            transcriptArray.put(entry);
            questionCount++;
        }

        return new TranscriptResult(transcriptArray.toString(2), questionCount);
    }

    @Override
    public Lesson createLesson(LessonDTO lessonDTO) {
        Topic topic = topicRepository.findById(lessonDTO.getTopicId())
                .orElseThrow(() -> new NoSuchElementException("Topic not found"));

        Level level = levelRepository.findById(lessonDTO.getLevelId())
                .orElseThrow(() -> new NoSuchElementException("Level not found"));

        TranscriptResult result = parseTranscript(lessonDTO.getTranscript());
        TranscriptResult translate = parseTranscript(lessonDTO.getTranslate());

        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);
        lesson.setLevelName(level.getName());
        lesson.setTranscript(result.transcriptJson);
        lesson.setQuestionCount(result.questionCount);
        lesson.setTranslate(translate.transcriptJson);
        return lessonRepository.save(lesson);
    }

    @Override
    public Lesson updateLesson(Long id, LessonDTO lessonDTO) {
        Lesson lesson = getLessonById(id);

        Level level = levelRepository.findById(lessonDTO.getLevelId())
                .orElseThrow(() -> new NoSuchElementException("Level không tồn tại"));

        Topic topic = topicRepository.findById(lessonDTO.getTopicId())
                .orElseThrow(() -> new NoSuchElementException("Topic không tồn tại"));

        TranscriptResult result = parseTranscript(lessonDTO.getTranscript());
        TranscriptResult translate = parseTranscript(lessonDTO.getTranslate());

        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);
        lesson.setLevelName(level.getName());
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);
        lesson.setTranscript(result.transcriptJson);
        lesson.setQuestionCount(result.questionCount);
        lesson.setTranslate(translate.transcriptJson);
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
