package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.AuthenticateDTO;
import com.wcs._2dolist.dto.TokenResponseDTO;
import com.wcs._2dolist.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    public TokenResponseDTO authentication(AuthenticateDTO loginRequest) {
        if(Objects.isNull(loginRequest.getEmail()) || Objects.isNull(loginRequest.getPassword())){
            throw new UsernameNotFoundException("Invalid user");
        }

        if(!userRepository.existsByEmail(loginRequest.getEmail())){
            throw new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail());
        }

        String token = jwtService.generateToken(loginRequest.getEmail());

//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getEmail(),
//                        passwordEncoder.encode(loginRequest.getPassword()),
//                        new ArrayList<>()
//                )
//        );

//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new TokenResponseDTO(token);
    }

}
