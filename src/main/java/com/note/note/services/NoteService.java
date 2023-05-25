package com.note.note.services;

import com.note.note.models.Note;
import com.note.note.models.User;
import com.note.note.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    // Methods for add, delete, update, get note
    public Note addNote (Note note){
        return noteRepository.save(note);
    }

    public void deleteNoteById(Long id){
        noteRepository.deleteById(id);
    }

    public Note updateNote (Note note){
        return noteRepository.save(note);
    }

    public Note getNoteById(Long id){
        return noteRepository.findById(id).get();
    }

    public Page<Note> getAllNotes(int page, int size){
        return noteRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Note> findNoteByTitle(String title, int page, int size){
        return noteRepository.findByTitleIgnoreCaseContains(PageRequest.of(page, size), title);
    }


    // Methods for get all notes and find notes by title

    public Page<Note> getAllNotesByCreator(User creator, int page, int size){
        return noteRepository.findAllByCreator(PageRequest.of(page, size), creator);
    }


}
