package com.project.englishweb.Service.API;
import com.project.englishweb.Entity.Flashcard;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Service.UserService;
import com.project.englishweb.repository.FlashcardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FlashcardService {
    @Autowired
    private FlashcardRepository flashcardRepository;
    @Autowired
    private UserService userService;
    public List<Flashcard> getAllFlashcardsByUser(Long userId) {
        return flashcardRepository.findByUserId(userId);
    }

    public Flashcard updateFlashcard(Long flashcardId, Flashcard newData) {
        Flashcard flashcard = flashcardRepository.findById(flashcardId)
                .orElseThrow(() -> new RuntimeException("Flashcard not found"));
        flashcard.setTitle(newData.getTitle());
        flashcard.setDescription(newData.getDescription());
        return flashcardRepository.save(flashcard);
    }
    public Flashcard createFlashcard(Long userId, Flashcard flashcard) {
        Optional<User> user = userService.getUserById(userId);
        if (user.isPresent()) {
            flashcard.setUser(user.get());
            return flashcardRepository.save(flashcard);
        }
        throw new RuntimeException("User not found");
    }
    public void deleteFlashcard(Long flashcardId) {
        flashcardRepository.deleteById(flashcardId);
    }

    public Flashcard getById(Long id) {
        return flashcardRepository.findById(id).orElseThrow(() -> new RuntimeException("Flashcard not found"));
    }
}
