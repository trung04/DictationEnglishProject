package com.project.englishweb.DTO;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Long questionId;
    private String url;
    private String answer;
    private Long lessonId;
}
