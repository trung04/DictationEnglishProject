package com.project.englishweb.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long topicId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String level;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @OneToMany(mappedBy = "Topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;
}
