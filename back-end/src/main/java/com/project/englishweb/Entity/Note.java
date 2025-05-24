package com.project.englishweb.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noteId;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}
