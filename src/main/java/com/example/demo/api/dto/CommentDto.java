package com.example.demo.api.dto;

import com.example.demo.api.model.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;

    @NotBlank
    private String content;

    @NotBlank
    private ZonedDateTime creationDateTime;

    @NotBlank
    private Long associatedPostId;

}
