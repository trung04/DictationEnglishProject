package com.project.englishweb.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
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


    @Column(columnDefinition = "TEXT")
    private String detail;

    @ManyToOne
    @JoinColumn(name = "levelId", nullable = false) // Khóa ngoại
    private Level level;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Topic parent; // Topic cha



    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons;
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }


    
    public String getDetail() {
        return detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }    

}
