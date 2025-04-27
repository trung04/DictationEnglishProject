package com.project.englishweb.DTO;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDTO {
    private String content;
    private int like;
    private int dislike;
    private LocalDateTime submittedAt;
    private Long userId;
    private Long questionId;
}