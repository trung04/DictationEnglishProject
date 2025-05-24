package com.project.englishweb.Controller;

import com.project.englishweb.DTO.NoteDTO;
import com.project.englishweb.Repository.UserRepository;
import com.project.englishweb.Service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listNotes(Model model) {
        model.addAttribute("notes", noteService.getAllNotes());
        return "notes/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("note", new NoteDTO());
        model.addAttribute("users", userRepository.findAll());
        return "notes/form";
    }

    @PostMapping("/save")
    public String saveNote(@ModelAttribute("note") NoteDTO note) {
        if (note.getNoteId() == null) {
            noteService.createNote(note);
        } else {
            noteService.updateNote(note.getNoteId(), note);
        }
        return "redirect:/notes";
    }

    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, Model model) {
        model.addAttribute("note", noteService.getNoteById(id));
        model.addAttribute("users", userRepository.findAll());
        return "notes/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/notes";
    }
}
