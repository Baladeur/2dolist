package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.AuthenticateDTO;
import com.wcs._2dolist.dto.TokenResponseDTO;
import com.wcs._2dolist.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody AuthenticateDTO loginRequest) {
        try {
            return ResponseEntity.ok(authService.authentication(loginRequest));
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new TokenResponseDTO(ex.getMessage()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TokenResponseDTO("Internal server error occurred: " + ex.getMessage()));
        }
    }

}
