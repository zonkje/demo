package com.example.demo.api.service;

import com.example.demo.api.dto.PostDto;
import com.example.demo.api.mapper.PostMapper;
import com.example.demo.api.model.Post;
import com.example.demo.api.repository.PostRepository;
import com.example.demo.auth.user.UserRepository;
import com.example.demo.auth.user.model.User;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
        User author = userRepository.findByUsername(authorName).get();
        post.setPostAuthor(author);
        return postMapper.toPostDto(postRepository.save(post), authorName);
    }

    public Collection<PostDto> getPosts() {
//        Change it later using streams()
        Iterable<Post> posts = postRepository.findAll();
        Collection<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts) {
//            We need somehow get author name and pass it to method below
            PostDto postDto = postMapper.toPostDto(post, "TestHardcodedAuthorName");
            postDtos.add(postDto);
        }
        return postDtos;
    }

    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId).get();
        String author = post.getPostAuthor().getFirstName();
        return postMapper.toPostDto(post, author);
    }

}
