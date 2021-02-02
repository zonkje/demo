package com.example.demo.api.mapper;

import com.example.demo.api.dto.CommentDto;
import com.example.demo.api.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "creationDateTime", expression = "java(java.time.ZonedDateTime.now())")
    Comment toComment(CommentDto commentDto);

    @Mapping(target = "associatedPostId", source = "associatedPostId")
    CommentDto toCommentDto(Comment comment, Long associatedPostId);

}
