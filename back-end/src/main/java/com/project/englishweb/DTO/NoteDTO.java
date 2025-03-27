package com.project.englishweb.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteDTO {
    private String note;
    private Long userId;
    private Long lessonId;
}
