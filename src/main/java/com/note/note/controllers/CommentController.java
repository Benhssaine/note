package com.note.note.controllers;

import com.note.note.models.Comment;
import com.note.note.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/comment/")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping("addCommentForm")
    public String addCommentForm(Model model){
        return "comment/addCommentForm";
    }

    @RequestMapping("addComment")
    public String addComment(@ModelAttribute("comment") Comment comment, Model model){
        commentService.addComment(comment);
        return getAllComments(model, 0);
    }

    @RequestMapping("deleteComment/{id}")
    public String deleteCommentById(@PathVariable("id") Long id, Model model){
        commentService.deleteCommentById(id);
        return getAllComments(model, 0);
    }

    @RequestMapping("updateCommentForm/{id}")
    public String updateCommentForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("comment", commentService.getCommentById(id));
        return "comment/updateCommentForm";
    }

    @RequestMapping("updateComment")
    public String updateComment(@ModelAttribute("comment") Comment comment, Model model){
        commentService.updateComment(comment);
        return getAllComments(model, 0);
    }

    @GetMapping(path = "getAllComments")
    public String getAllComments(Model model,
                                 @RequestParam(value = "page", defaultValue = "0") int page) {

        Page<Comment> commentPage = commentService.getAllComments(page, 4);
        model.addAttribute("commentList", commentPage.getContent());
        model.addAttribute("pages", new int[commentPage.getTotalPages()]);
        model.addAttribute("currentPage", page);

        return "comment/getAllComments";
    }
}
