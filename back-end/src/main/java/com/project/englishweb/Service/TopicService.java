package com.project.englishweb.Service;

import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    // Lấy tất cả topics
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    // Lấy topic theo ID
    public Optional<Topic> getTopicById(Long id) {
        return topicRepository.findById(id);
    }

    // Thêm mới một topic
    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    // Cập nhật topic
    public Topic updateTopic(Long id, Topic topicDetails) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic không tồn tại"));
        topic.setTitle(topicDetails.getTitle());
        topic.setLevelId(topicDetails.getLevelId());
        topic.setDetail(topicDetails.getDetail());
        return topicRepository.save(topic);
    }

    // Xóa topic
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}
