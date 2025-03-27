package com.project.englishweb.Service;

import com.project.englishweb.Entity.Note;
import com.project.englishweb.DTO.NoteDTO;

import java.util.List;

public interface NoteService {
    Note createNote(NoteDTO noteDTO);
    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note updateNote(Long id, NoteDTO noteDTO);
    void deleteNote(Long id);
}
