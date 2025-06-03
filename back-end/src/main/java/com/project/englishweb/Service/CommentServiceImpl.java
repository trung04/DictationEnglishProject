package com.project.englishweb.Service;

import com.project.englishweb.Entity.Comment;
import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Repository.CommentRepository;
import com.project.englishweb.Repository.LessonRepository;
import com.project.englishweb.Repository.UserRepository;
import com.project.englishweb.DTO.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    @Override
    public Comment createComment(CommentDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Lesson lesson = lessonRepository.findById(commentDTO.getLessonId())
                .orElseThrow(() -> new NoSuchElementException("Lesson not found"));
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setLike(commentDTO.getLike());
        comment.setDislike(commentDTO.getDislike());
        comment.setSubmittedAt(LocalDateTime.now());
        comment.setUser(user);
        comment.setLesson(lesson);
        return commentRepository.save(comment);
    }


    @Override
    public Page<Comment> getAllComments(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }
    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Comment not found"));
    }

    @Override
    public Comment updateComment(Long id, CommentDTO commentDTO) {
        Comment comment = getCommentById(id);
        comment.setContent(commentDTO.getContent());
        comment.setLike(commentDTO.getLike());
        comment.setDislike(commentDTO.getDislike());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


    @Override
    public List<Comment> getCommentsByUserId(Long userId) {
        return commentRepository.findByUserUserId(userId);
    }

}
