package com.example.demo.api.controller;

import com.example.demo.api.dto.PostDto;
import com.example.demo.api.model.Post;
import com.example.demo.api.repository.PostRepository;
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
    private final PostRepository postRepository;

    @Autowired
    public PostController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }


    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_USER')")
    public PostDto createPost(@RequestBody PostDto postDto,
                              Authentication authentication) {
        return postService.createPost(postDto, authentication.getName());
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public Collection<PostDto> getAllPosts(){
//        return postRepository.findAll();
        return postService.getPosts();
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public PostDto getPost(@PathVariable("postId") Long postId){
        return postService.getPost(postId);
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
    }

    @PatchMapping("/{postId}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public PostDto updatePost(@PathVariable("postId") Long postId, @RequestBody PostDto postDto) {
        return postService.updatePost(postId, postDto);
    }

}
