package com.project.englishweb.Entity;

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
    private Long id;

    private int lessonStatus;
    private int attempts;
    private LocalDateTime completedAt;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "lessonId", nullable = false)
    private Lesson lesson;
}
