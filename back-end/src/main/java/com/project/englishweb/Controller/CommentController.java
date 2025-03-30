package com.project.englishweb.Controller;

import com.project.englishweb.DTO.CommentDTO;
import com.project.englishweb.Entity.Comment;
import com.project.englishweb.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(commentDTO));
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.updateComment(id, commentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<Comment>> getCommentsByQuestionId(@PathVariable Long questionId) {
        return ResponseEntity.ok(commentService.getCommentsByQuestionId(questionId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Comment>> getCommentsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(commentService.getCommentsByUserId(userId));
    }
    @GetMapping("/question/{questionId}/newest")
    public ResponseEntity<List<Comment>> getCommentsByQuestionIdOrderBySubmittedAtDesc(@PathVariable Long questionId) {
        return ResponseEntity.ok(commentService.getCommentsByQuestionIdOrderBySubmittedAtDesc(questionId));
    }
    @GetMapping("/question/{questionId}/like")
    public ResponseEntity<List<Comment>> getCommentsByQuestionIdOrderByLikeDesc(@PathVariable Long questionId) {
        return ResponseEntity.ok(commentService.getCommentsByQuestionIdOrderByLikeDesc(questionId));
    }

    @GetMapping("/question/{questionId}/dislike")
    public ResponseEntity<List<Comment>> getCommentsByQuestionIdOrderByDislikeDesc(@PathVariable Long questionId) {
        return ResponseEntity.ok(commentService.getCommentsByQuestionIdOrderByDislikeDesc(questionId));
    }
}