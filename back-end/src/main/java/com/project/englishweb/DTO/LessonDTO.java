package com.project.englishweb.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LessonDTO {
    private String title;
    private Long levelId;
    private String URL;
    private Long topicId;
    private String levelName;
    private String transcript;
    private int questionCount;
}
