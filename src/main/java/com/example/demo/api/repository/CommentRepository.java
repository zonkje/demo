package com.example.demo.api.repository;

import com.example.demo.api.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Post, Long> {
}
