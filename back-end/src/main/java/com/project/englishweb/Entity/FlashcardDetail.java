package com.project.englishweb.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "flashcard_details")
public class FlashcardDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String term;

    private String meaning;

    // Quan hệ ManyToOne tới Flashcard
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flashcard_id", nullable = false)
    @JsonBackReference
    private Flashcard flashcard;

    public FlashcardDetail() {}

    public FlashcardDetail(String term, String meaning, Flashcard flashcard) {
        this.term = term;
        this.meaning = meaning;
        this.flashcard = flashcard;
    }

    // Getters và setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public Flashcard getFlashcard() {
        return flashcard;
    }

    public void setFlashcard(Flashcard flashcard) {
        this.flashcard = flashcard;
    }
}
