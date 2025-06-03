package com.project.englishweb.Service;

import com.project.englishweb.Entity.Comment;
import com.project.englishweb.DTO.CommentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    Comment createComment(CommentDTO commentDTO);
    Page<Comment> getAllComments(Pageable pageable);
    Comment getCommentById(Long id);
    Comment updateComment(Long id, CommentDTO commentDTO);
    void deleteComment(Long id);
    List<Comment> getCommentsByUserId(Long userId);
}
