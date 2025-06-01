package com.project.englishweb.Service.API;
import com.project.englishweb.DTO.FlashcardDetailDTO;
import com.project.englishweb.Entity.Flashcard;
import com.project.englishweb.Entity.FlashcardDetail;
import com.project.englishweb.repository.FlashcardDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
@Service
public class FlashcardDetailService {
    private final FlashcardDetailRepository flashcardDetailRepository;
    private final FlashcardService flashcardService; // để lấy flashcard theo ID

    @Autowired
    public FlashcardDetailService(FlashcardDetailRepository flashcardDetailRepository, FlashcardService flashcardService) {
        this.flashcardDetailRepository = flashcardDetailRepository;
        this.flashcardService = flashcardService;
    }
    public List<FlashcardDetail> findAll() {
        return flashcardDetailRepository.findAll();
    }
    public List<FlashcardDetail> findByFlashcardId(Long flashcardId) {
        return flashcardDetailRepository.findByFlashcardId(flashcardId);
    }

    public Optional<FlashcardDetail> findById(Long id) {
        return flashcardDetailRepository.findById(id);
    }


    public FlashcardDetail createFlashcardDetail(FlashcardDetailDTO dto) {
        Flashcard flashcard = flashcardService.getById(dto.getFlashcardId());

        FlashcardDetail detail = new FlashcardDetail();
        detail.setTerm(dto.getTerm());
        detail.setMeaning(dto.getMeaning());
        detail.setFlashcard(flashcard);

        return flashcardDetailRepository.save(detail);
    }

    public FlashcardDetail update(Long id, FlashcardDetailDTO flashcardDetail) {
        return flashcardDetailRepository.findById(id)
                .map(existing -> {
                    existing.setTerm(flashcardDetail.getTerm());
                    existing.setMeaning(flashcardDetail.getMeaning());
                    return flashcardDetailRepository.save(existing);
                }).orElseThrow(() -> new RuntimeException("FlashcardDetail not found with id " + id));
    }

    public void deleteById(Long id) {
        flashcardDetailRepository.deleteById(id);
    }

    public List<FlashcardDetail> getDetailsByFlashcardId(Long flashcardId) {
        return flashcardDetailRepository.findByFlashcardId(flashcardId);
    }
    public FlashcardDetail getById(Long id) {
        return flashcardDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flashcard detail not found with id: " + id));
    }


}
