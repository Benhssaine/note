package com.note.note.repositories;

import com.note.note.models.Note;
import com.note.note.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Page<Note> findByTitleIgnoreCaseContains(Pageable pageable, String input);

    Page<Note> findAllByCreator(Pageable pageable, User creator);
}
