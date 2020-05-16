package com.example.demo.api.mapper;

import com.example.demo.api.dto.PostDto;
import com.example.demo.api.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "creationDateTime", expression = "java(java.time.ZonedDateTime.now())")
    Post toPost(PostDto postDto);

    @Mapping(target = "authorName", source = "postAuthorName")
    PostDto toPostDto(Post post, String postAuthorName);
}
