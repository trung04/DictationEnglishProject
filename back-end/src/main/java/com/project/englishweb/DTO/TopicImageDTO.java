package com.project.englishweb.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TopicImageDTO {
    private Long id;
    private String fileName;
    private String filePath;
    private Long topicId;
} 