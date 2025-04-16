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
    List<Lesson> findByLevelId(Long levelId);
    List<Lesson> findByTitle(String title);


    @Query("SELECT l FROM Lesson l WHERE " +
            "(:title IS NULL OR LOWER(l.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:levelId IS NULL OR :level = '' OR l.levelId = :levelId) AND " + // Thêm điều kiện kiểm tra chuỗi rỗng cho level
            "(:topicId IS NULL OR l.topic.topicId = :topicId)")
    Page<Lesson> findByTitleAndLevelAndTopicIdWithSearch(
            @Param("title") String title,
            @Param("levelId") Long levelId,
            @Param("topicId") Long topicId,
            Pageable pageable
    );
}
