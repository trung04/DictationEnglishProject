package com.project.englishweb.Service;

import com.project.englishweb.DTO.NoteDTO;
import com.project.englishweb.DTO.ProgressDTO;
import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Note;
import com.project.englishweb.Entity.Progress;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Repository.ProgressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProgressService {
    @Autowired
    private ProgressRepository progressRepository;
    @Autowired
    private com.project.englishweb.Repository.UserRepository userRepository;
    @Autowired
    private com.project.englishweb.Repository.LessonRepository lessonRepository;
    public Progress addOrUpdateProgress(ProgressDTO progressDTO) {
        User user = userRepository.findById(progressDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Lesson lesson = lessonRepository.findById(progressDTO.getLessonId())
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Optional<Progress> existingProgressOpt = progressRepository.findByUserIdAndLessonId(user.getUserId(), lesson.getLessonId());

        Progress progress;
        if (existingProgressOpt.isPresent()) {
            // Cập nhật bản ghi hiện có
            progress = existingProgressOpt.get();
            progress.setAttempts(progressDTO.getAttempts());
            progress.setLessonStatus(progressDTO.getLessonStatus());
            progress.setCompletedAt(LocalDateTime.now());
        } else {
            // Tạo bản ghi mới
            progress = new Progress();
            progress.setUser(user);
            progress.setLesson(lesson);
            progress.setAttempts(progressDTO.getAttempts());
            progress.setLessonStatus(progressDTO.getLessonStatus());
            progress.setCompletedAt(LocalDateTime.now());
        }

        return progressRepository.save(progress);
    }

    public void update(Long progressId){
        Optional<Progress> pro = progressRepository.findById(progressId);
        if (pro.isPresent()) {
            Progress progress = pro.get();
            progress.setLessonStatus(1);
            progressRepository.save(progress);
        } else {
            throw new RuntimeException("Progress với ID " + progressId + " không tồn tại.");
        }


    }

    public void delete(Long progressId) {
        if(!progressRepository.existsById(progressId)) {
            throw new RuntimeException("Progress not found");
        }
        progressRepository.deleteById(progressId);


    }
    public List<ProgressDTO> getProgressesByUserId(Long userId) {
        return progressRepository.findByUserId(userId);
    }


}
