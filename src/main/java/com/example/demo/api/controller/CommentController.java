package com.example.demo.api.controller;

import com.example.demo.api.dto.CommentDto;
import com.example.demo.api.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/comment")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    @Autowired

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public CommentDto

}
