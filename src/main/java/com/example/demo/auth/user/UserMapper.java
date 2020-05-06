package com.example.demo.auth.user;

import com.example.demo.auth.user.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userToDto(UserDto userDto);
    //  Automatically generated method return user with only common fields for both classes
    //  I need also initialize fields that are not included in source (dto) class object
    //  Editing UserMapperImpl is not the best idea.
    //  option 1: add custom constructor in User class
    //  option 2: something with default method?

}
