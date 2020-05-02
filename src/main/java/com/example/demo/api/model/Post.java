package com.example.demo.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@TableGenerator(name = "posts")
@Data
@NoArgsConstructor(force = true)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

}
