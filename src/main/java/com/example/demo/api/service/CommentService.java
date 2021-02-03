package com.example.demo.api.service;

import com.example.demo.api.dto.CommentDto;
import com.example.demo.api.exception.PostNotFoundException;
import com.example.demo.api.mapper.CommentMapper;
import com.example.demo.api.model.Comment;
import com.example.demo.api.model.Post;
import com.example.demo.api.repository.CommentRepository;
import com.example.demo.api.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
    }

    public Collection<CommentDto> getComments(Long postId){
        return commentRepository.findAll()
                .stream()
                .filter(comment -> comment.getAssociatedPost().getId()==postId)
                .map(comment -> commentMapper.toCommentDto(comment, postId))
                .collect(Collectors.toList());
    }

    public CommentDto addComment(CommentDto commentDto){
        Comment comment = commentMapper.toComment(commentDto);
        Long associatedPostId = commentDto.getAssociatedPostId();
        Post associatedPost = postRepository.findById(associatedPostId)
                .orElseThrow(() -> new PostNotFoundException(associatedPostId));
        comment.setAssociatedPost(associatedPost);
        return commentMapper.toCommentDto(commentRepository.save(comment), associatedPostId);
    }

}
