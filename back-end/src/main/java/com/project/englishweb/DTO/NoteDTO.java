package com.project.englishweb.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
    private Long noteId;
    private String content;

    private Long userId;
    private String username;

    private Long lessonId;
    private String lessonTitle;
}

