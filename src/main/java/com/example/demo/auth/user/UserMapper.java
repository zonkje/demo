package com.example.demo.auth.user;

import com.example.demo.auth.security.passwordencode.EncodedMapping;
import com.example.demo.auth.security.passwordencode.PasswordEncoderMapper;
import com.example.demo.auth.user.model.User;
import org.apache.catalina.security.SecurityUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring",
        uses = PasswordEncoderMapper.class
        )
public interface UserMapper {
    /*
            there's some strange behaviour: hint for this method says:
            Unmapped target properties: accountNonExpired, accountNonLocked, credentialsNonExpired, enabled
            somehow he suggest wrong fields names. If I change these names according to hint, compiler drop me error
            Error:(23, 10) java: Unknown property "{suggestedFieldName}" in result type com.example.demo.auth.user.model.User. Did you mean "null"?

            I might be wrong but his suggestions are somewhat correct, cause maybe I shouldn't give them a names that starts with 'is...'?
    */
    @Mapping(target = "isEnabled", constant = "true")
    @Mapping(target = "isCredentialsNonExpired", constant = "true")
    @Mapping(target = "isAccountNonLocked", constant = "true")
    @Mapping(target = "isAccountNonExpired", constant = "true")
    @Mapping(target = "role", expression = "java( com.example.demo.auth.user.model.UserRole.USER)")
    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    User toUser(UserDto userDto);

    UserDto toUserDto(User user);

}
