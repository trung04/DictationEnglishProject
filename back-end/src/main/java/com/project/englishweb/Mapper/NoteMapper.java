package com.project.englishweb.Mapper;

import com.project.englishweb.DTO.NoteDTO;
import com.project.englishweb.Entity.Note;
import com.project.englishweb.Entity.User;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public NoteDTO toDTO(Note note) {
        if (note == null) return null;
        return new NoteDTO(
                note.getNoteId(),
                note.getContent(),
                note.getUser().getUserId(),
                note.getUser().getUsername()
        );
    }

    public Note toEntity(NoteDTO dto, User user) {
        Note note = new Note();
        note.setNoteId(dto.getNoteId());
        note.setContent(dto.getContent());
        note.setUser(user);
        return note;
    }
}
