package com.project.englishweb.Repository;

import com.project.englishweb.Entity.TopicImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TopicImageRepository extends JpaRepository<TopicImage, Long> {
    List<TopicImage> findByTopic_TopicId(Long topicId);


}