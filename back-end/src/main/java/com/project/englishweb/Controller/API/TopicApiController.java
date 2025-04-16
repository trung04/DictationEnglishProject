package com.project.englishweb.Controller.API;

import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/topics")

@CrossOrigin(origins = "http://localhost:3000")

public class TopicApiController {

    private final TopicService topicService;

    @Autowired
    public TopicApiController(TopicService topicService) {
        this.topicService = topicService;
    }
    // Lấy tất cả Topics
    @GetMapping
    public ResponseEntity<List<Topic>> getAllTopics() {
        List<Topic> topics = topicService.getAllTopics();
        return ResponseEntity.ok(topics);
    }


}
