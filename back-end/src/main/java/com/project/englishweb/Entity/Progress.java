package com.project.englishweb.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "progress")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long progressId;

    private int lessonStatus;
    private int attempts;
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "lessonId", nullable = false)
    @JsonBackReference
    private Lesson lesson;

    public void setProgressId(long progressId) {
        this.progressId = progressId;
    }

    public void setLessonStatus(int lessonStatus) {
        this.lessonStatus = lessonStatus;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public long getProgressId() {
        return progressId;
    }

    public int getLessonStatus() {
        return lessonStatus;
    }

    public int getAttempts() {
        return attempts;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public User getUser() {
        return user;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLessonId(long lessonId) {
    }
}
