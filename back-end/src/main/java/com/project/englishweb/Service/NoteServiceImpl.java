package com.project.englishweb.Service;

import com.project.englishweb.Entity.Lesson;
import com.project.englishweb.Entity.Note;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Repository.LessonRepository;
import com.project.englishweb.Repository.NoteRepository;
import com.project.englishweb.Repository.UserRepository;
import com.project.englishweb.DTO.NoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;

    @Override
    public Note createNote(NoteDTO noteDTO) {
        User user = userRepository.findById(noteDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Lesson lesson = lessonRepository.findById(noteDTO.getLessonId()).orElseThrow(() -> new RuntimeException("Lesson not found"));

        Note note = new Note();
        note.setNote(noteDTO.getNote());
        note.setUser(user);
        note.setLesson(lesson);

        return noteRepository.save(note);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public Note updateNote(Long id, NoteDTO noteDTO) {
        Note note = getNoteById(id);
        note.setNote(noteDTO.getNote());
        return noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}