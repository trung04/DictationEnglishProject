package com.project.englishweb.Service;

import com.project.englishweb.DTO.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAllNotes();
    NoteDTO getNoteById(Long id);
    NoteDTO createNote(NoteDTO dto);
    NoteDTO updateNote(Long id, NoteDTO dto);
    void deleteNote(Long id);
}
