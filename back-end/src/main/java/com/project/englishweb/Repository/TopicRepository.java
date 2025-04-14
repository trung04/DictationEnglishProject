package com.project.englishweb.Repository;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Topic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    // List<Topic> findByTitle(String title);
    // List<Topic> findByLevel(String level);
    // List<Topic> findByDetail(String detail);

    // @Query("SELECT t FROM Topic t WHERE " +
    //     "(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
    //     "(:level IS NULL OR :level = '' OR t.level = :level)")
    // Page<Topic> findByTitleAndLevelWithSearch(
    //     @Param("title") String title,
    //     @Param("level") String level,
    //     Pageable pageable
    // );

}
