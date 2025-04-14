package com.project.englishweb.Service;

import com.project.englishweb.DTO.TopicDTO;
import com.project.englishweb.Entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TopicService {
    List<TopicDTO> getAllTopics();
    Page<Topic> getAllTopics(Pageable pageable);         // ✅ dùng trong controller
    TopicDTO getTopicById(Long id);
    Topic getTopicEntityById(Long id);                   // ✅ dùng khi sửa
    TopicDTO createTopic(TopicDTO topicDTO);
    TopicDTO updateTopic(Long id, TopicDTO topicDTO);
    void deleteTopic(Long id);
    Page<Topic> searchTopics(String name, Pageable pageable); // nếu có ô tìm kiếm
}
