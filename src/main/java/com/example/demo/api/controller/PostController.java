package com.example.demo.api.controller;

import com.example.demo.api.dto.PostDto;
import com.example.demo.api.model.Post;
import com.example.demo.api.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = "/post")
@CrossOrigin(origins = "*")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public PostDto createPost(@RequestBody PostDto postDto,
                              Authentication authentication) {
        return postService.createPost(postDto, authentication.getName());
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public Collection<PostDto> getAllPosts(){
        return postService.getPosts();
    }

}
