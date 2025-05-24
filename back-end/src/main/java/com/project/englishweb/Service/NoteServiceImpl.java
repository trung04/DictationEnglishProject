package com.project.englishweb.Service;

import com.project.englishweb.DTO.NoteDTO;
import com.project.englishweb.Entity.Note;
import com.project.englishweb.Entity.User;
import com.project.englishweb.Mapper.NoteMapper;
import com.project.englishweb.Repository.NoteRepository;
import com.project.englishweb.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteMapper noteMapper;

    @Override
    public List<NoteDTO> getAllNotes() {
        return noteRepository.findAll()
                .stream()
                .map(noteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDTO getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with ID: " + id));
        return noteMapper.toDTO(note);
    }

    @Override
    public NoteDTO createNote(NoteDTO dto) {
        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUsername()));
        Note note = noteMapper.toEntity(dto, user);
        return noteMapper.toDTO(noteRepository.save(note));
    }

    @Override
    public NoteDTO updateNote(Long id, NoteDTO dto) {
        Note existing = noteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Note not found with ID: " + id));

        User user = userRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + dto.getUsername()));

        existing.setContent(dto.getContent());
        existing.setUser(user);

        return noteMapper.toDTO(noteRepository.save(existing));
    }

    @Override
    public void deleteNote(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new EntityNotFoundException("Note not found with ID: " + id);
        }
        noteRepository.deleteById(id);
    }
}
