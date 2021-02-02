package com.example.demo.api.controller;

import com.example.demo.api.dto.CommentDto;
import com.example.demo.api.dto.PostDto;
import com.example.demo.api.model.Comment;
import com.example.demo.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/comment")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    // DOESN'T WORK
    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public Collection<Comment> getCommentsFromPost(@PathVariable("/{postId}") Long postId){
        return commentService.getComments(postId);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_USER')")
    public CommentDto addComment(@RequestBody CommentDto commentDto) {
        return commentService.addComment(commentDto);
    }

}
