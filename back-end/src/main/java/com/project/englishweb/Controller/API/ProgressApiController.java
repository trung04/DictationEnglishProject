package com.project.englishweb.Controller.API;

import com.project.englishweb.DTO.ProgressDTO;
import com.project.englishweb.DTO.NoteDTO;
import com.project.englishweb.Entity.Progress;
import com.project.englishweb.Service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Cho phép React truy cập
@RestController
@RequestMapping(path = "/api/progress")

public class ProgressApiController
{
    @Autowired
    private ProgressService progressService;
    @GetMapping("/hi")
    public String hello(){
        return "Hello World";
    }
    @PostMapping(value = "/add",consumes = {"*/*"})
    public  ResponseEntity<Progress> test(@RequestParam int lessonStatus,
                                          @RequestParam int attempts,
                                          @RequestParam Long userId,
                                          @RequestParam Long lessonId){
        ProgressDTO pro = new ProgressDTO(lessonStatus, attempts, userId, lessonId);
        Progress savedProgress = progressService.addOrUpdateProgress(pro);

        return ResponseEntity.ok(savedProgress);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteNote(@PathVariable Long id) {
        progressService.delete(id);
    }
    @GetMapping("/update-progress-status/{progressId}")
    public ResponseEntity<String> updateProgressStatus(@PathVariable Long progressId){
        try {
            progressService.update(progressId);
            return ResponseEntity.ok("Cập nhật thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Không tìm thấy progress ID: " + progressId);
        }
    }





    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProgressDTO>> getProgressesByUserId(@PathVariable Long userId) {
        List<ProgressDTO> progresses = progressService.getProgressesByUserId(userId);
        return ResponseEntity.ok(progresses);
    }


}
