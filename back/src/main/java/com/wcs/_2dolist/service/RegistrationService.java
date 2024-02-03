package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.RegistrationRequestDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.enums.UserStatus;
import com.wcs._2dolist.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final HashingService hashingService;

    public RegistrationService(
            UserRepository userRepository,
            EmailService emailService,
            HashingService hashingService
    ) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.hashingService = hashingService;
    }

    public void initiateRegistration(RegistrationRequestDTO registrationRequest) throws IllegalStateException {
        String email = registrationRequest.getEmail();

        if (userRepository.existsByEmail(email)) {
            throw new IllegalStateException("Email address already exists");
        }

        String registrationHash = hashingService.generateUniqueHash(email);

        User user = new User();
        user.setEmail(email);
        user.setRegistrationToken(registrationHash);
        user.setDateRequestRegistrationToken(new Date());
        user.setStatus(UserStatus.REGISTRATION_LINK_SENT);

        userRepository.save(user);

        emailService.sendRegistrationEmail(email, registrationHash, registrationRequest.getFrontUrl());
    }

    public boolean verifyRegistrationToken(String token) {
        User user = userRepository.findByRegistrationToken(token);
        if (user == null || user.getDateRequestRegistrationToken() == null) {
            return false;
        }

        long twentyFourHoursAgoMillis = System.currentTimeMillis() - (24 * 60 * 60 * 1000);

        if (user.getDateRequestRegistrationToken().getTime() < twentyFourHoursAgoMillis) {
            return false;
        }

        user.setStatus(UserStatus.REGISTRATION_LINK_CHECKED);
        userRepository.save(user);
        return true;
    }


}

