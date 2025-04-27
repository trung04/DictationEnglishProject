package com.project.englishweb.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicResponseDTO {
    private Long topicId;
    private String title;
    private String detail;
    private String levelName;
    private Long parentId;
} 