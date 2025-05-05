package com.project.englishweb.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "lessons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lessonId;

    @Column(nullable = false)
    private String title;
    @Column(name = "level")
    private String levelName;
    @ManyToOne
    @JoinColumn(name = "level_Id", nullable = false) // Khóa ngoại
    private Level level;

    private String URL;

    @Column(name = "transcript")
    private String transcript;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false) // Khóa ngoại
    @JsonBackReference
    private Topic topic;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Progress> progresses;


}
