package com.note.note.services;

import com.note.note.models.Comment;
import com.note.note.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    // Methods for add, delete, update, get comment
    public Comment addComment (Comment comment){
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long id){
        commentRepository.deleteById(id);
    }

    public Comment updateComment (Comment comment){
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long id){
        return commentRepository.findById(id).get();
    }

    public Page<Comment> getAllComments(int page, int size){
        return commentRepository.findAll(PageRequest.of(page, size));
    }

    // Method for get all comments

    public Page<Comment> getAllCommentsByNoteId(Long noteId, int page, int size){
        return commentRepository.findAllByNoteId(PageRequest.of(page, size), noteId);
    }
}
