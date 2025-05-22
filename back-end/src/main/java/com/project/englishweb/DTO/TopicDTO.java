package com.project.englishweb.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicDTO {
    private String title;
    private String detail;
    private Long levelId;
    private Long parentId;
    private String parentImagePath;
}

