package com.example.demo.api.model;

import com.example.demo.auth.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private final Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_date_time")
    private ZonedDateTime creationDateTime;

    @ManyToOne
    @JoinColumn(name = "post_author_id")
    private User postAuthor;

}
