package com.project.englishweb.Repository;

import com.project.englishweb.Entity.Topic;
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
}
