package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.AuthenticateDTO;
import com.wcs._2dolist.dto.TokenResponseDTO;
import com.wcs._2dolist.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthenticateDTO loginRequest) {
        System.out.println("loginRequest = " + loginRequest.getEmail() + " " + loginRequest.getPassword());
        TokenResponseDTO tokenResponse = authService.login(loginRequest);
        return ResponseEntity.ok(tokenResponse);
    }

}
