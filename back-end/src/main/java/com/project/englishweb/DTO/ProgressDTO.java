package com.project.englishweb.DTO;

import com.project.englishweb.Entity.Lesson;

public class ProgressDTO {
        private Long progressId;
        private int lessonStatus;
        private int attempts;
        private Long userId;
        private Long lessonId;
        private Lesson lesson;

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
    public ProgressDTO() {

    }
    public ProgressDTO(int lessonStatus, int attempts, Long userId, Long lessonId) {
        this.lessonStatus = lessonStatus;
        this.attempts = attempts;
        this.userId = userId;
        this.lessonId = lessonId;

    }


    public ProgressDTO(Long progressId,int lessonStatus, int attempts, Long userId, Long lessonId, Lesson lesson) {
        this.lessonStatus = lessonStatus;
        this.attempts = attempts;
        this.userId = userId;
        this.lessonId = lessonId;
        this.lesson=lesson;
        this.progressId=progressId;
    }
    public int getLessonStatus() {
        return lessonStatus;
    }

    public Long getProgressId() {
        return progressId;
    }

    public void setLessonStatus(int lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    // Getters and Setters


}
