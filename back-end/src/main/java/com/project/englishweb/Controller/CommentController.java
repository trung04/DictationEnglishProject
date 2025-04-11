package com.project.englishweb.Controller;

import com.project.englishweb.DTO.CommentDTO;
import com.project.englishweb.Entity.Comment;
import com.project.englishweb.Service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService = null;

    @GetMapping
    public String manageComments(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(required = false) String action,
                                 @RequestParam(required = false) Long id,
                                 Model model) {
        if ("new".equals(action)) {
            model.addAttribute("commentDTO", new CommentDTO());
            model.addAttribute("action", "new");
            model.addAttribute("activePage", "comments");
            return "comments/manage";
        }

        if (id != null) {
            Comment comment = commentService.getCommentById(id);
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setContent(comment.getContent());
            commentDTO.setQuestionId(comment.getQuestion().getQuestionId());
            commentDTO.setUserId(comment.getUser().getUserId());
            commentDTO.setLike(comment.getLike());
            commentDTO.setDislike(comment.getDislike());
            model.addAttribute("commentDTO", commentDTO);
            model.addAttribute("commentId", id);
            model.addAttribute("activePage", "comments");
            return "comments/manage";
        }

        Pageable pageable = PageRequest.of(page, 10);
        Page<Comment> commentsPage = commentService.getAllComments(pageable);

        model.addAttribute("comments", commentsPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", commentsPage.getTotalPages());
        model.addAttribute("activePage", "comments");
        return "comments/manage";
    }

    @PostMapping
    public String createComment(@ModelAttribute CommentDTO commentDTO) {
        commentService.createComment(commentDTO);
        return "redirect:/comments";
    }

    @PostMapping("/update/{id}")
    public String updateComment(@PathVariable Long id, @ModelAttribute CommentDTO commentDTO) {
        commentService.updateComment(id, commentDTO);
        return "redirect:/comments";
    }

    @GetMapping("/delete/{id}")
    public String deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return "redirect:/comments";
    }
}

