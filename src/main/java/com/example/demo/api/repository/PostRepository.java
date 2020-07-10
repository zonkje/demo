package com.example.demo.api.repository;

import com.example.demo.api.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

}
