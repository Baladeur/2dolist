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
    public ResponseEntity<Void> initiateRegistration(@RequestBody RegistrationRequestDTO registrationRequest) {
        registrationService.initiateRegistration(registrationRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<String> confirmRegistration(@PathVariable("hash") String hash) {
        if (hash == null || hash.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid registration hash or registration already completed.");
        }
        return ResponseEntity.ok("Registration completed successfully!");
    }
}
