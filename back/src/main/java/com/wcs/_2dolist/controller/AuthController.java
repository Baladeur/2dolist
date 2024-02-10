package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.AccessTokenResponseDTO;
import com.wcs._2dolist.dto.AuthenticateDTO;
import com.wcs._2dolist.dto.TokensRequestDTO;
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
    public ResponseEntity<TokensRequestDTO> login(@RequestBody AuthenticateDTO loginRequest) {
        try {
            return ResponseEntity.ok(authService.authentication(loginRequest));
        } catch (UsernameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new TokensRequestDTO(ex.getMessage(), false));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TokensRequestDTO("Internal server error occurred: " + ex.getMessage(), false));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AccessTokenResponseDTO> refreshAccessToken(@RequestBody TokensRequestDTO request) {
        try {
            return ResponseEntity.ok(authService.refreshAccessToken(request));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new AccessTokenResponseDTO("Internal server error occurred: " + ex.getMessage(), false));
        }
    }


}
