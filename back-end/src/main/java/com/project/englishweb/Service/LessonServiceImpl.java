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

        String[] lines = lessonDTO.getTranscript().split("\n");
        StringBuilder transcriptString = new StringBuilder();
        String start = "0:00";  // Bắt đầu từ 0:00 (hoặc thời gian bắt đầu của transcript đầu tiên)
        int id = 1;
        String end = "";
        int QuestionCount = 0;
        // Duyệt qua từng dòng transcript
        for (String line : lines) {
            String[] parts = line.trim().split(" ", 2);  // Tách phần đầu là thời gian, phần còn lại là văn bản
            if (parts.length < 2) {
                if(parts[0].charAt(parts[0].length()-3)==':'){
                    QuestionCount++;
                    end = parts[0].trim();
                    continue;
                }
            }

            String text = line;

            transcriptString.append("Id: ").append(String.valueOf(id))
                    .append(" Start: ").append(start)
                    .append(" End: ").append(end)
                    .append(" Text: ").append(text)
                    .append("\n");

            // Cập nhật thời gian bắt đầu cho dòng tiếp theo
            start = end;
            id++;
        }

        // Chuyển transcript thành String
        String transcript = transcriptString.toString();

        // Tạo bài học mới và lưu vào cơ sở dữ liệu
        Lesson lesson = new Lesson();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);  // Gán level từ DTO
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);
        lesson.setLevelName(level.getName());
        lesson.setTranscript(transcript);  // Lưu transcript dưới dạng String
        lesson.setQuestionCount(QuestionCount);

        // Lưu bài học vào cơ sở dữ liệu
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
        StringBuilder transcriptString = new StringBuilder();
        String start = "0:00";
        int ID = 1;
        String end = "";
        int QuestionCount = 0;
        // Duyệt qua từng dòng transcript
        for (String line : lines) {
            String[] parts = line.trim().split(" ", 2);
            if (parts.length < 2) {
                if(parts[0].charAt(parts[0].length()-3)==':'){
                    QuestionCount++;
                    end = parts[0].trim();
                    continue;
                }
            }

            String text = line;
            transcriptString.append("Id: ").append(String.valueOf(ID))
                    .append(" Start: ").append(start)
                    .append(" End: ").append(end)
                    .append(" Text: ").append(text)
                    .append("\n");
            start = end;
            ID++;
        }
        String transcript = transcriptString.toString();
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setLevel(level);
        lesson.setLevelName(level.getName());
        lesson.setURL(lessonDTO.getURL());
        lesson.setTopic(topic);
        lesson.setTranscript(transcript);
        lesson.setQuestionCount(QuestionCount);
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
