package com.project.englishweb.Service;

import com.project.englishweb.DTO.TopicDTO;
import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Repository.TopicRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public Topic createTopic(TopicDTO topicDTO) {
        Topic topic = new Topic();
        topic.setTitle(topicDTO.getTitle());
        topic.setLevel(topicDTO.getLevel());
        topic.setDetail(topicDTO.getDetail());
        return topicRepository.save(topic);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Page<Topic> getAllTopics(Pageable pageable) {
        return topicRepository.findAll(pageable);
    }

    @Override
    public Topic getTopicById(Long id) {
        return topicRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Topic not found"));
    }

    @Override
    public Topic updateTopic(Long id, TopicDTO topicDTO) {
        Topic topic = getTopicById(id);
        topic.setTitle(topicDTO.getTitle());
        topic.setLevel(topicDTO.getLevel());
        topic.setDetail(topicDTO.getDetail());
        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }

    @Override
    public List<Topic> getTopicsByTitle(String title) {
        return topicRepository.findByTitle(title);
    }

    @Override
    public List<Topic> getTopicsByLevel(String level) {
        return topicRepository.findByLevel(level);
    }

    @Override
    public List<Topic> getTopicsByDetail(String detail) {
        return topicRepository.findByDetail(detail);
    }

    @Override
    public Page<Topic> searchTopics(String title, String level, Pageable pageable) {
        return topicRepository.findByTitleAndLevelWithSearch(title, level, pageable);
    }
}
