package com.project.englishweb.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "topic_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
} 