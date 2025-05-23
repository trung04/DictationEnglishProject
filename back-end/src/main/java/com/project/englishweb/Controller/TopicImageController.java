package com.project.englishweb.Controller;

import com.project.englishweb.DTO.TopicImageDTO;
import com.project.englishweb.Service.TopicImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/topic-images")
public class TopicImageController {

    private final TopicImageService topicImageService;

    public TopicImageController(TopicImageService topicImageService) {
        this.topicImageService = topicImageService;
    }

    @PostMapping("/upload/{topicId}")
    public ResponseEntity<TopicImageDTO> uploadImage(
            @PathVariable Long topicId,
            @RequestParam("file") MultipartFile file) {
        TopicImageDTO uploadedImage = topicImageService.uploadImage(topicId, file);
        return ResponseEntity.ok(uploadedImage);
    }

    @GetMapping("/topic/{topicId}")
    public ResponseEntity<List<TopicImageDTO>> getImagesByTopicId(@PathVariable Long topicId) {
        List<TopicImageDTO> images = topicImageService.getImagesByTopicId(topicId);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        topicImageService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }
} 