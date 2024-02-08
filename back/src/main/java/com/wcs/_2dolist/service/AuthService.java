package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.LoginRequestDTO;
import com.wcs._2dolist.dto.RegistrationRequestDTO;
import com.wcs._2dolist.dto.TokenResponseDTO;
import com.wcs._2dolist.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;

//    public AuthService(AuthenticationManager authenticationManager,
    public AuthService(
                       JwtTokenProvider jwtTokenProvider
//                       UserService userService
                       ) {
//                       PasswordEncoder passwordEncoder) {
//        this.authenticationManager = authenticationManager;
        //    private final AuthenticationManager authenticationManager;
        //        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponseDTO login(LoginRequestDTO loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        String token = jwtTokenProvider.generateToken(userDetails);
//        return new TokenResponseDTO(token);
        return null;
    }

//    public void register(RegistrationRequestDTO registrationRequest) {
//        String encodedPassword = passwordEncoder.encode(registrationRequest.getPassword());
//        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
//        loginRequestDTO.setEmail(registrationRequest.getUsername());
//        loginRequestDTO.setPassword(encodedPassword);
//        userService.createUser(loginRequestDTO);
//    }

}
