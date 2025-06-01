package com.project.englishweb.Controller.API;
import com.project.englishweb.DTO.FlashcardDetailDTO;
import com.project.englishweb.Entity.Flashcard;
import com.project.englishweb.Entity.FlashcardDetail;
import com.project.englishweb.Service.API.FlashcardDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/flashcard-details")
@CrossOrigin(origins = "http://localhost:3000") //
public class FlashcardDetailController {
    private final FlashcardDetailService flashcardDetailService;
    @Autowired
    public FlashcardDetailController(FlashcardDetailService flashcardDetailService) {
        this.flashcardDetailService = flashcardDetailService;
    }
    // API lấy danh sách FlashcardDetail theo flashcardId
    @GetMapping("/by-flashcard/{flashcardId}")
    public ResponseEntity<List<FlashcardDetail>> getByFlashcardId(@PathVariable Long flashcardId) {
        List<FlashcardDetail> details = flashcardDetailService.findByFlashcardId(flashcardId);
        if (details.isEmpty()) {
            return ResponseEntity.noContent().build();  // Nếu không có dữ liệu trả về 204 No Content
        }
        return ResponseEntity.ok(details); // Trả về 200 OK và dữ liệu
    }
    // ✅ Tạo mới FlashcardDetail
    @PostMapping("/create")
    public ResponseEntity<FlashcardDetail> create(@RequestBody @Valid FlashcardDetailDTO dto) {
        FlashcardDetail saved = flashcardDetailService.createFlashcardDetail(dto);
        return ResponseEntity.ok(saved);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        flashcardDetailService.deleteById(id);
    }
    @GetMapping("/{id}")
    public FlashcardDetail getFlashCardById(@PathVariable Long id){
        return flashcardDetailService.getById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlashcardDetail> updateFlashcardDetail(
            @PathVariable Long id,
            @RequestBody FlashcardDetailDTO flashcardDetail) {
        FlashcardDetail updated = flashcardDetailService.update(id, flashcardDetail);
        return ResponseEntity.ok(updated);
    }

}
