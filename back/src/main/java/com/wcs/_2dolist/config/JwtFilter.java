package com.wcs._2dolist.config;

import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public JwtFilter(JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO: find better way to check if the request is for registration or auth
        if(
                request.getRequestURI().startsWith("/registration") ||
                request.getRequestURI().startsWith("/auth")
        ){
            filterChain.doFilter(request,response);
            return;
        }

        String token = jwtService.retrieveToken(request);

        User user = jwtService.validateAndReturnUsername(token)
                .orElseThrow(() -> new AuthorizationServiceException("User not found !"));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                new ArrayList<>()
        );

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request,response);

    }
}
