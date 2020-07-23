package com.example.demo.auth.jwt;

import com.example.demo.auth.security.JwtSecretKey;
import com.example.demo.auth.user.UserRepository;
import com.example.demo.auth.user.UserRepositoryService;
import com.example.demo.auth.user.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public class JwtSignInFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final SecretKey secretKey;
    private final UserRepository userRepository;

    public JwtSignInFilter(AuthenticationManager authenticationManager, SecretKey secretKey, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.secretKey = secretKey;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {
            SignInRequest signInRequest = new ObjectMapper()
                    .readValue(request.getInputStream(), SignInRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    signInRequest.getUsername(),
                    signInRequest.getPassword()
            );

            return authenticationManager.authenticate(authentication);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(20)))
                .signWith(secretKey)
                .compact();

        Optional<User> userOptional = userRepository.findByUsername(authResult.getName());
        Long signingUserId = userOptional.get().getId();

        response.addHeader("Access-Control-Expose-Headers", "UserID");
        response.addHeader("UserID", signingUserId+"");
        response.addHeader("Access-Control-Expose-Headers", HttpHeaders.AUTHORIZATION);
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer "+token);
    }
}
