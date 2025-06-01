package com.project.englishweb.Controller.API;
import com.project.englishweb.Entity.Flashcard;
import com.project.englishweb.Service.API.FlashcardService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin(origins = "http://localhost:3000") // Cho phép React truy cập

@RequestMapping("/api/flashcards")
public class FlashcardController {
    @Autowired
    private FlashcardService flashcardService;
    @GetMapping("/user/{userId}")
    public List<Flashcard> getByUser(@PathVariable Long userId) {
        return flashcardService.getAllFlashcardsByUser(userId);
    }

    @GetMapping("/{flashcardId}")
    public Flashcard getFlashCardById(@PathVariable Long flashcardId){
        return flashcardService.getById(flashcardId);

    }
    @PostMapping(value = "/user/{userId}")
    public Flashcard create(
            @PathVariable Long userId,
            @RequestParam String title,
            @RequestParam String description
    ) {
        Flashcard a = new Flashcard(title, description);
        return flashcardService.createFlashcard(userId, a);
    }
    @PutMapping("/{id}")
    public Flashcard update(@PathVariable Long id, @RequestParam String title,
                            @RequestParam String description ) {
        Flashcard newFlashcard= flashcardService.getById(id);
        newFlashcard.setTitle(title);
        newFlashcard.setDescription(description);
        return flashcardService.updateFlashcard(id, newFlashcard);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        flashcardService.deleteFlashcard(id);
    }
}
