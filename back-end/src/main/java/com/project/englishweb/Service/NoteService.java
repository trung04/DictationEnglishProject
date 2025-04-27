package com.project.englishweb.Service;

import com.project.englishweb.DTO.NoteDTO;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAllNotes();
    NoteDTO getNoteById(Long id);
    NoteDTO createNote(NoteDTO noteDTO);
    NoteDTO updateNote(Long id, NoteDTO noteDTO);
    void deleteNote(Long id);
}
