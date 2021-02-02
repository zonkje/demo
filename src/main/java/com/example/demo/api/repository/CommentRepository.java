package com.example.demo.api.repository;

import com.example.demo.api.model.Comment;
import com.example.demo.api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByAssociatedPost(Long postId);

}
