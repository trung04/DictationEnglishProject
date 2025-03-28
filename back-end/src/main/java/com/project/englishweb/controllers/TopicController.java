package com.project.englishweb.controllers;

import com.project.englishweb.Entity.Topic;
import com.project.englishweb.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")
public class TopicController {

    private final TopicService topicService;

    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    // Lấy tất cả Topics
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }

    // Lấy Topic theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Topic> getTopicById(@PathVariable Long id) {
        Optional<Topic> topic = topicService.getTopicById(id);
        return topic.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Thêm mới một Topic
    @PostMapping
    public ResponseEntity<Topic> createTopic(@RequestBody Topic topic) {
        Topic createdTopic = topicService.createTopic(topic);
        return ResponseEntity.ok(createdTopic);
    }

    // Cập nhật Topic theo ID
    @PutMapping("/{id}")
    public ResponseEntity<Topic> updateTopic(@PathVariable Long id, @RequestBody Topic topicDetails) {
        try {
            Topic updatedTopic = topicService.updateTopic(id, topicDetails);
            return ResponseEntity.ok(updatedTopic);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa Topic theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }
}
