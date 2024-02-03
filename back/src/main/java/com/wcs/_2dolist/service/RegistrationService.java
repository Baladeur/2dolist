package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.RegistrationCompleteDTO;
import com.wcs._2dolist.dto.RegistrationRequestDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.enums.UserRole;
import com.wcs._2dolist.enums.UserStatus;
import com.wcs._2dolist.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final HashingService hashingService;
    private final PasswordEncoder passwordEncoder;
    public RegistrationService(
            UserRepository userRepository,
            EmailService emailService,
            HashingService hashingService,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.hashingService = hashingService;
        this.passwordEncoder = passwordEncoder;
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

        long threeHoursAgoMillis = System.currentTimeMillis() - (3 * 60 * 60 * 1000);

        if (user.getDateRequestRegistrationToken().getTime() < threeHoursAgoMillis) {
            return false;
        }

        user.setStatus(UserStatus.REGISTRATION_LINK_CHECKED);
        userRepository.save(user);
        return true;
    }

    public void completeRegistration(RegistrationCompleteDTO registrationCompleteDTO) {
        String email = registrationCompleteDTO.getEmail();
        String password = registrationCompleteDTO.getPassword();
        String token = registrationCompleteDTO.getRegistrationToken();

        User user = userRepository.findByEmail(email);
        if (
            Objects.isNull(user) ||
            Objects.isNull(user.getRegistrationToken()) ||
            !user.getRegistrationToken().equals(token)
        ) {
            throw new IllegalArgumentException("Invalid registration token or email");
        }

        long threeHoursAgoMillis = System.currentTimeMillis() - (3 * 60 * 60 * 1000);

        if (user.getDateRequestRegistrationToken().getTime() < threeHoursAgoMillis) {
            throw new IllegalStateException("Registration token has expired");
        }

        // TODO: check user password strength and throw exception if not strong enough

        user.setPassword(passwordEncoder.encode(password));
        user.setStatus(UserStatus.ACTIVE);
        user.setEmailVerified(true);
        user.setRole(UserRole.ADMIN);
        user.setRegistrationToken(null);
        user.setDateRegistrationCompleted(new Date());
        user.setLastUpdatedDate(new Date());

        userRepository.save(user);
    }
}
