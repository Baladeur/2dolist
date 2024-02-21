package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.RegistrationCompleteDTO;
import com.wcs._2dolist.dto.RegistrationRequestDTO;
import com.wcs._2dolist.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> initiateRegistration(@RequestBody RegistrationRequestDTO registrationRequest) {
        try {
            registrationService.initiateRegistration(registrationRequest);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Registration initiated successfully");
            return ResponseEntity.ok(responseBody);
        } catch (IllegalStateException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Email address already exists or blocked, " +
                    "or registration link has been requested in the last 3 hours");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<Map<String, String>> checkRegistrationToken(@RequestBody Map<String, String> requestBody) {
        String registrationToken = requestBody.get("registrationToken");
        Map<String, String> responseBody = new HashMap<>();
        if (registrationToken == null || registrationToken.isEmpty()) {
            responseBody.put("message", "Invalid registration token");
            return ResponseEntity.badRequest().body(responseBody);
        }
        if (registrationService.verifyRegistrationToken(registrationToken)) {
            responseBody.put("message", "Registration token is valid and registration can be completed.");
            return ResponseEntity.ok(responseBody);
        } else {
            responseBody.put("message", "Invalid registration token or registration expired.");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }

    @PostMapping("/complete")
    public ResponseEntity<Map<String, String>> completeRegistration(@RequestBody RegistrationCompleteDTO registrationCompleteDTO) {
        try {
            registrationService.completeRegistration(registrationCompleteDTO);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Registration completed successfully");
            return ResponseEntity.ok(responseBody);
        } catch (IllegalArgumentException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Invalid registration data");
            return ResponseEntity.badRequest().body(responseBody);
        } catch (IllegalStateException e) {
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", "Invalid registration token or registration expired");
            return ResponseEntity.badRequest().body(responseBody);
        }
    }
}
