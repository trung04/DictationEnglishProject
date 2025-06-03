package com.project.englishweb.Service.API;
import com.project.englishweb.DTO.CommentApiDTO;
import com.project.englishweb.Entity.Comment;
import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Repository.CommentRepository;
import com.project.englishweb.Repository.UserRepository;
import com.project.englishweb.Repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentApiSevice {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    public CommentApiSevice(CommentRepository commentRepository, LessonRepository lessonRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
    }
    public Comment createComment(CommentApiDTO commentDTO) {
        User user = userRepository.findById(commentDTO.getUserId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Lesson lesson = lessonRepository.findById(commentDTO.getLessonId())
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setSubmittedAt(LocalDateTime.now());
        comment.setLesson(lesson);
        comment.setUser(user);
        return commentRepository.save(comment);
    }
    public List<Comment> findAll(){
          return  commentRepository.findAll();
    }
    public String delete(Long commentId){
        commentRepository.deleteById(commentId);
        return "success";
    }
        public Comment update(Long commentId,String newContent){
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new RuntimeException("Comment not found with id " + commentId));
            comment.setContent(newContent);
            return commentRepository.save(comment);
    }
}
