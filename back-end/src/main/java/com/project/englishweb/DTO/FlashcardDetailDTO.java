package com.project.englishweb.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlashcardDetailDTO {

    @NotBlank(message = "Term is required")
    private String term;

    @NotBlank(message = "Meaning is required")
    private String meaning;

    @NotNull(message = "Flashcard ID is required")
    private Long flashcardId;

    // Getters và setters
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

    public Long getFlashcardId() {
        return flashcardId;
    }

    public void setFlashcardId(Long flashcardId) {
        this.flashcardId = flashcardId;
    }
}

