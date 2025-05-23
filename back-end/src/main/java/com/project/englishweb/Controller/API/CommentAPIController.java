package com.project.englishweb.Controller.API;
import com.project.englishweb.DTO.CommentApiDTO;
import com.project.englishweb.DTO.CommentDTO;
import com.project.englishweb.Entity.Comment;
import com.project.englishweb.Service.API.CommentApiSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000") // Cho phép React truy cập
@RestController
@RequestMapping(path = "/api/comment")

public class CommentAPIController
{
    @Autowired
    private CommentApiSevice commentApiSevice;

    @GetMapping("/hi")
    public String hello(){
        return "Hello World";
    }
    @PostMapping("/addComment")
    public Comment addComment(@RequestBody CommentApiDTO commentDTO) {
        return commentApiSevice.createComment(commentDTO);
    }
    @GetMapping("/list")
    public List<Comment> findAllComment(){
        return commentApiSevice.findAll();
    }


}
