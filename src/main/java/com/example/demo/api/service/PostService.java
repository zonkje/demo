package com.example.demo.api.service;

import com.example.demo.api.dto.PostDto;
import com.example.demo.api.exception.PostNotFoundException;
import com.example.demo.api.mapper.PostMapper;
import com.example.demo.api.model.Post;
import com.example.demo.api.repository.PostRepository;
import com.example.demo.auth.user.UserRepository;
import com.example.demo.auth.user.model.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    @Autowired
    public PostService(PostRepository postRepository, PostMapper postMapper, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.userRepository = userRepository;
    }

    public PostDto createPost(PostDto postDto, String authorName) {
        // this works fine, but I think this logic (setting author to new post) should be contained in mapper
        Post post = postMapper.toPost(postDto);
        User author = userRepository.findByUsername(authorName)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", authorName)
                ));
        post.setPostAuthor(author);
        return postMapper.toPostDto(postRepository.save(post), authorName);
    }

    public Collection<PostDto> getPosts() {
        return Lists.newArrayList(postRepository.findAll())
                .stream()
                .map(post -> {
                    String authorName = post.getPostAuthor().getFirstName();
                    return postMapper.toPostDto(post, authorName);
                })
                .collect(Collectors.toList());
    }

    public PostDto getPost(Long postId) {

        return postRepository.findById(postId).map(post -> {
                    String author = post.getPostAuthor().getFirstName();
                    return postMapper.toPostDto(post, author);
                }
        ).orElseThrow(() -> new PostNotFoundException(postId));
//        Post post = postRepository.findById(postId).get();
//        String author = post.getPostAuthor().getFirstName();
//        return postMapper.toPostDto(post, author);
    }

    public PostDto updatePost(Long postId, PostDto postDto) {
        return postRepository.findById(postId).map(post -> {
                    if (postDto.getContent() != null) {
                        post.setContent(postDto.getContent());
                    }
                    if (postDto.getTitle() != null) {
                        post.setTitle(postDto.getTitle());
                    }
                    return postMapper.toPostDto(postRepository.save(post), post.getPostAuthor().getFirstName());
                }
        ).orElseThrow(() -> new PostNotFoundException(postId));
//        Post updatedPost = postRepository.findById(postId).get();
//        if(postDto.getContent()!=null){
//            updatedPost.setContent(postDto.getContent());
//        }
//        if(postDto.getTitle()!=null){
//            updatedPost.setTitle(postDto.getTitle());
//        }
//        return postMapper.toPostDto(postRepository.save(updatedPost), updatedPost.getPostAuthor().getFirstName());
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

}
