package com.project.englishweb.Repository;

import com.project.englishweb.Entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByParentIsNull();

    @Query("SELECT t FROM Topic t WHERE t.parent.topicId = :parentId")
    List<Topic> findByParentTopicId(@Param("parentId") Long parentId);

    @Query("SELECT t FROM Topic t WHERE " +
            "(:title IS NULL OR LOWER(t.title) LIKE LOWER(CONCAT('%', :title, '%'))) AND " +
            "(:levelName IS NULL OR LOWER(t.level.name) LIKE LOWER(CONCAT('%', :levelName, '%')))")
    Page<Topic> findByTitleAndLevelNameWithSearch(
            @Param("title") String title,
            @Param("levelName") String levelName,
            Pageable pageable
    );

    @Query("SELECT t FROM Topic t WHERE t.parent IS NOT NULL")
    Page<Topic> findByParentIsNotNull(Pageable pageable);
}
