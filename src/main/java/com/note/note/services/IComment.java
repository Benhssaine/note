package com.note.note.services;

import com.note.note.models.Comment;
import org.springframework.data.domain.Page;

public interface IComment {
    Comment addComment(Comment comment);
    void deleteCommentById(Long id);
    Comment getCommentById(Long id);
    Page<Comment> getAllComments(int page, int size);
}
