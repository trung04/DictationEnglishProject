package com.project.englishweb.Service;

import com.project.englishweb.DTO.TopicImageDTO;
import com.project.englishweb.Entity.Topic;
import com.project.englishweb.Entity.TopicImage;
import com.project.englishweb.Repository.TopicImageRepository;
import com.project.englishweb.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TopicImageServiceImpl implements TopicImageService {

    @Value("${upload.path}")
    private String uploadPath;

    private final TopicImageRepository topicImageRepository;
    private final TopicRepository topicRepository;

    public TopicImageServiceImpl(TopicImageRepository topicImageRepository, TopicRepository topicRepository) {
        this.topicImageRepository = topicImageRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public TopicImageDTO uploadImage(Long topicId, MultipartFile file) {
        try {
            Topic topic = topicRepository.findById(topicId)
                    .orElseThrow(() -> new RuntimeException("Topic not found"));

            // Tạo thư mục nếu chưa tồn tại
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Tạo tên file ngẫu nhiên
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);

            // Lưu file
            Files.copy(file.getInputStream(), filePath);

            // Lưu thông tin vào database
            TopicImage topicImage = new TopicImage();
            topicImage.setFileName(fileName);
            topicImage.setFilePath(filePath.toString());
            topicImage.setTopic(topic);

            TopicImage savedImage = topicImageRepository.save(topicImage);

            return convertToDTO(savedImage);
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload image", e);
        }
    }

    @Override
    public List<TopicImageDTO> getImagesByTopicId(Long topicId) {
        return topicImageRepository.findByTopicId(topicId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteImage(Long imageId) {
        TopicImage image = topicImageRepository.findById(imageId)
                .orElseThrow(() -> new RuntimeException("Image not found"));

        try {
            // Xóa file
            Files.deleteIfExists(Paths.get(image.getFilePath()));
            // Xóa record trong database
            topicImageRepository.delete(image);
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image", e);
        }
    }

    private TopicImageDTO convertToDTO(TopicImage image) {
        return new TopicImageDTO(
                image.getId(),
                image.getFileName(),
                image.getFilePath(),
                image.getTopic().getTopicId()
        );
    }
} 