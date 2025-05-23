package com.project.englishweb.Service;

import com.project.englishweb.DTO.TopicImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TopicImageService {
    TopicImageDTO uploadImage(Long topicId, MultipartFile file);
    List<TopicImageDTO> getImagesByTopicId(Long topicId);
    void deleteImage(Long imageId);
} 