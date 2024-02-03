package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.RegistrationRequestDTO;
import com.wcs._2dolist.service.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<String> initiateRegistration(@RequestBody RegistrationRequestDTO registrationRequest) {
        try {
            registrationService.initiateRegistration(registrationRequest);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Email address already exists");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<String> checkRegistrationToken(@PathVariable("token") String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid registration token");
        }
        if (registrationService.verifyRegistrationToken(token)) {
            return ResponseEntity.ok("Registration token is valid and registration can be completed.");
        } else {
            return ResponseEntity.badRequest().body("Invalid registration token or registration expired.");
        }
    }
}
