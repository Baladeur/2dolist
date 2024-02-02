package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.RegistrationRequestDTO;
import com.wcs._2dolist.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final HashingService hashingService;

    public RegistrationService(UserRepository userRepository, EmailService emailService, HashingService hashingService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.hashingService = hashingService;
    }

    public void initiateRegistration(RegistrationRequestDTO registrationRequest) {
        String email = registrationRequest.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("Email address already exists");
        }

        String registrationHash = hashingService.generateUniqueHash(email);

        emailService.sendRegistrationEmail(email, registrationHash);
    }
}

