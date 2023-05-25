package com.note.note.services;

import com.note.note.models.Note;
import org.springframework.data.domain.Page;

public interface INote {
    void addNote(Note note);
    void deleteNoteById(Long id);
    void updateNote(Note note);
    Note getNoteById(Long id);
    Page<Note> getAllNotes(int page, int size);
    Page<Note> findNoteByTitle(String title, int page, int size);
}
