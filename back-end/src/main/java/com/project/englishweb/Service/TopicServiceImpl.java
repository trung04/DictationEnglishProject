package com.project.englishweb.Service;

import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Entity.Level;
import com.project.englishweb.DTO.TopicDTO;
import com.project.englishweb.Repository.TopicRepository;
import com.project.englishweb.Repository.LevelRepository;
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
    private final LevelRepository levelRepository;

    @Override
    public Topic createTopic(TopicDTO topicDTO) {
        Level level = levelRepository.findById(topicDTO.getLevelId())
                .orElseThrow(() -> new NoSuchElementException("Level not found with id: " + topicDTO.getLevelId()));

        Topic parent = null;
        if (topicDTO.getParentId() != null) {
            parent = topicRepository.findById(topicDTO.getParentId())
                    .orElseThrow(() -> new NoSuchElementException("Parent topic not found with id: " + topicDTO.getParentId()));
        }

        Topic topic = new Topic();
        topic.setTitle(topicDTO.getTitle());
        topic.setDetail(topicDTO.getDetail());
        topic.setLevel(level);
        topic.setParent(parent);
        topic.setParentImagePath(topicDTO.getParentImagePath());

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
                .orElseThrow(() -> new NoSuchElementException("Topic not found with id: " + id));
    }

    @Override
    public Topic updateTopic(Long id, TopicDTO topicDTO) {
        Topic topic = getTopicById(id);
        Level level = levelRepository.findById(topicDTO.getLevelId())
                .orElseThrow(() -> new NoSuchElementException("Level not found with id: " + topicDTO.getLevelId()));

        Topic parent = null;
        if (topicDTO.getParentId() != null) {
            parent = topicRepository.findById(topicDTO.getParentId())
                    .orElseThrow(() -> new NoSuchElementException("Parent topic not found with id: " + topicDTO.getParentId()));
        }

        topic.setTitle(topicDTO.getTitle());
        topic.setDetail(topicDTO.getDetail());
        topic.setLevel(level);
        topic.setParent(parent);
        topic.setParentImagePath(topicDTO.getParentImagePath());

        return topicRepository.save(topic);
    }

    @Override
    public void deleteTopic(Long id) {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Topic not found with id: " + id));

        // Kiểm tra xem topic có phải là parent của topic khác không
        List<Topic> childTopics = topicRepository.findByParentTopicId(id);
        if (!childTopics.isEmpty()) {
            throw new IllegalStateException("Cannot delete topic because it is a parent of other topics. Please delete or reassign child topics first.");
        }

        topicRepository.delete(topic);
    }

    @Override
    public List<Topic> findByParentIsNull() {
        return topicRepository.findByParentIsNull();
    }

    @Override
    public List<Topic> findByParentTopicId(Long parentId) {
        return topicRepository.findByParentTopicId(parentId);
    }

    @Override
    public Page<Topic> searchTopics(String title, String levelName, Pageable pageable) {
        return topicRepository.findByTitleAndLevelNameWithSearch(title, levelName, pageable);
    }

    @Override
    public Page<Topic> findByParentIsNotNull(Pageable pageable) {
        return topicRepository.findByParentIsNotNull(pageable);
    }
} 