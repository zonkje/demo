package com.example.demo.api.model;

import com.example.demo.auth.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Data
@Table(name = "comments")
@NoArgsConstructor(force = true)
@Builder
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private final Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_date_time")
    private ZonedDateTime creationDateTime;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post associatedPost;

}
