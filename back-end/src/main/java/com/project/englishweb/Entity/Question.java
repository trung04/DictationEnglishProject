package com.project.englishweb.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    private String url;

    @Column(nullable = false)
    private String answer;

    @ManyToOne
    @JoinColumn(name = "lessonId", nullable = false)
    private Lesson lesson;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
    public String getUrl() {
        return url;
    }
    
    // public void setUrl(String url) {
    //     this.url = url;
    // }
    
    // public String getAnswer() {
    //     return answer;
    // }
    
    // public void setAnswer(String answer) {
    //     this.answer = answer;
    // }
    
    // public Lesson getLesson() {
    //     return lesson;
    // }
    
    // public void setLesson(Lesson lesson) {
    //     this.lesson = lesson;
    // }
    
}
