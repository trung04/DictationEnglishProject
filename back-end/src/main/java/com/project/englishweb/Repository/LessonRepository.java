package com.project.englishweb.Repository;

import com.project.englishweb.Entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByTopicTopicId(Long topicId);
    List<Lesson> findByTitle(String title);

    @Query("SELECT l FROM Lesson l WHERE " +
            "(:title IS NULL OR LOWER(l.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:levelName IS NULL    OR LOWER(l.level.name) LIKE LOWER(CONCAT('%', :levelName, '%'))) AND " +
            "(:topicTitle IS NULL OR LOWER(l.topic.title) LIKE LOWER(CONCAT('%', :topicTitle, '%')))")  // Tìm kiếm theo topic.title
    Page<Lesson> findByTitleAndLevelNameAndTopicTitleWithSearch(
            @Param("title") String title,
            @Param("levelName") String levelName,
            @Param("topicTitle") String topicTitle,
            Pageable pageable
    );
}
