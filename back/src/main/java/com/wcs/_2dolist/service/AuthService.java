package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.AuthenticateDTO;
import com.wcs._2dolist.dto.TokenResponseDTO;
import com.wcs._2dolist.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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


    public TokenResponseDTO authentication(AuthenticateDTO loginRequest) throws UsernameNotFoundException{
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if(Objects.isNull(email) || Objects.isNull(password)){
            throw new UsernameNotFoundException("Invalid user");
        }

        if(!userRepository.existsByEmail(email)){
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        String token = jwtService.generateToken(email);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new TokenResponseDTO(token);
    }

}
