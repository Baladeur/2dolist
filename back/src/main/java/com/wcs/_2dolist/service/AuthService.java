package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.AccessTokenResponseDTO;
import com.wcs._2dolist.dto.AuthenticateDTO;
import com.wcs._2dolist.dto.TokensRequestDTO;
import com.wcs._2dolist.exception.InvalidAccessTokenException;
import com.wcs._2dolist.exception.InvalidRefreshTokenException;
import com.wcs._2dolist.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public TokensRequestDTO authentication(AuthenticateDTO loginRequest) throws UsernameNotFoundException{
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if(Objects.isNull(email) || Objects.isNull(password)){
            throw new UsernameNotFoundException("Invalid user");
        }

        if(!userRepository.existsByEmail(email)){
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtService.generateTokens(email);
    }

    public AccessTokenResponseDTO refreshAccessToken(TokensRequestDTO request) {
        try {
            return jwtService.refreshAccessToken(request);
        } catch (InvalidRefreshTokenException | InvalidAccessTokenException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
        }
    }

}
