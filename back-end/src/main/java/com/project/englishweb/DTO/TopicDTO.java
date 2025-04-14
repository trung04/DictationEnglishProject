package com.project.englishweb.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicDTO {
    private Long topicId;
    private String title;
    private String level;
    private String detail;
}
