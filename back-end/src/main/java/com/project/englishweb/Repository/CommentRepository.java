package com.project.englishweb.Repository;

import com.project.englishweb.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByQuestionQuestionId(Long questionId);
    List<Comment> findByUserUserId(Long userId);
    List<Comment> findByQuestionQuestionIdOrderBySubmittedAtDesc(Long questionId);
    List<Comment> findByQuestionQuestionIdOrderByLikeDesc(Long questionId);
    List<Comment> findByQuestionQuestionIdOrderByDislikeDesc(Long questionId);
}