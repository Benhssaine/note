package com.note.note.controllers;

import com.note.note.models.Note;
import com.note.note.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/admin/note/")
public class NoteController {
    @Autowired
    private NoteService noteService;

    @RequestMapping("addNoteForm")
    public String addNoteForm(Model model){
        model.addAttribute("note", new Note());
        model.addAttribute("allCategories", Locale.Category.values());
        return "note/addNoteForm";
    }


    @RequestMapping("addNote")
    public String addNote(@ModelAttribute("note") Note note, Model model){
        noteService.addNote(note);
        return getAllNotes(model, 0);
    }

    @RequestMapping("deleteNote/{id}")
    public String deleteNoteById(@PathVariable("id") Long id, Model model){
        noteService.deleteNoteById(id);
        return getAllNotes(model, 0);
    }

    @RequestMapping("updateNoteForm/{id}")
    public String updateNoteForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("note", noteService.getNoteById(id));
        return "note/updateNoteForm";
    }

    @RequestMapping("updateNote")
    public String updateNote(@ModelAttribute("note") Note note, Model model){
        noteService.updateNote(note);
        return getAllNotes(model, 0);
    }

        @GetMapping(path = "getAllNotes")
        public String getAllNotes(Model model,
                                  @RequestParam(value = "page", defaultValue = "0") int page) {

            Page<Note> notePage = noteService.getAllNotes(page, 4);
            model.addAttribute("noteList", notePage.getContent());

            model.addAttribute("pages", new int[notePage.getTotalPages()]);
            model.addAttribute("currentPage", page);

            return "note/getAllNotes";
        }
}
