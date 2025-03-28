package com.project.englishweb.Service;

import com.project.englishweb.Entity.Comment;
import com.project.englishweb.DTO.CommentDTO;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentDTO commentDTO);
    List<Comment> getAllComments();
    Comment getCommentById(Long id);
    Comment updateComment(Long id, CommentDTO commentDTO);
    void deleteComment(Long id);
    List<Comment> getCommentsByQuestionId(Long questionId);
    List<Comment> getCommentsByUserId(Long userId);
    List<Comment> getCommentsByQuestionIdOrderBySubmittedAtDesc(Long questionId);
    List<Comment> getCommentsByQuestionIdOrderByLikeDesc(Long questionId);
    List<Comment> getCommentsByQuestionIdOrderByDislikeDesc(Long questionId);
}