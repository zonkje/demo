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

    public Collection<Comment> getComments(Long postId){
        Collection<Comment> comments = commentRepository.findAllByAssociatedPost(postId);
        return comments;
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
