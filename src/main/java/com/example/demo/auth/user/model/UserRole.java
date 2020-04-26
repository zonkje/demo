package com.example.demo.auth.user.model;

import com.example.demo.auth.user.model.UserAuthority;
import com.google.common.collect.Sets;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.auth.user.model.UserAuthority.*;

@NoArgsConstructor(force = true) //??
@RequiredArgsConstructor
public enum UserRole {

    USER(Sets.newHashSet(POST_READ, POST_WRITE, FIXTURE_READ)),
    ADMIN(Sets.newHashSet(POST_READ, POST_WRITE, FIXTURE_READ, FIXTURE_WRITE));

    private final Set<UserAuthority> authorities;

    public Set<UserAuthority> getAuthorities(){
        return authorities;
    }

    /**
     * Returns full collection of permissions, including roles and authorities
     *
     * @return  full permissions collection
     * */
    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){

        Set<SimpleGrantedAuthority> authorities = getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toSet());

        authorities.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return authorities;
    }

}
