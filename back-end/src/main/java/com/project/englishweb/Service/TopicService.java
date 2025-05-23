package com.project.englishweb.Service;

import com.project.englishweb.Entity.Topic;
import com.project.englishweb.DTO.TopicDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface TopicService {
    Topic createTopic(TopicDTO topicDTO);
    List<Topic> getAllTopics();
    Page<Topic> getAllTopics(Pageable pageable);
    Topic getTopicById(Long id);
    Topic updateTopic(Long id, TopicDTO topicDTO);
    void deleteTopic(Long id);
    List<Topic> findByParentIsNull();
    List<Topic> findByParentTopicId(Long parentId);
    Page<Topic> searchTopics(String title, String levelName, Pageable pageable);
    Page<Topic> findByParentIsNotNull(Pageable pageable);
}
