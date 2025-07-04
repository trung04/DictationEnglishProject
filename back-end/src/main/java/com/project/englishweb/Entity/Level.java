package com.project.englishweb.Entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "levels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "level_id") // Đúng với database
    private Long LevelId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

}
