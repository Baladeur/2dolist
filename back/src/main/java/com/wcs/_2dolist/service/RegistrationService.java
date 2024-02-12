package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.RegistrationCompleteDTO;
import com.wcs._2dolist.dto.RegistrationRequestDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.enums.UserRole;
import com.wcs._2dolist.enums.UserStatus;
import com.wcs._2dolist.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${registration-token.lifetime-millis}")
    private long registrationTokenLifetimeMillis;

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
        User existingUser = userRepository.findByEmail(email);

        if (existingUser != null) {

            // if user is active or blocked or if user has requested registration link in the last 3 hours
            if (
                existingUser.getStatus() == UserStatus.ACTIVE ||
                existingUser.getStatus() == UserStatus.BLOCKED || (
                    (existingUser.getStatus() == UserStatus.REGISTRATION_LINK_SENT ||
                    existingUser.getStatus() == UserStatus.REGISTRATION_LINK_CHECKED) &&
                existingUser.getDateRequestRegistrationToken()
                        .getTime() > System.currentTimeMillis() - registrationTokenLifetimeMillis)
            ) {
                throw new IllegalStateException("Email address already exists or blocked," +
                        "or registration link has been requested in the last 3 hours");
            }

            String registrationToken = hashingService.generateRegistrationToken(email);

            existingUser.setRegistrationToken(hashingService.generateRegistrationToken(email));
            existingUser.setDateRequestRegistrationToken(new Date());
            existingUser.setStatus(UserStatus.REGISTRATION_LINK_SENT);
            userRepository.save(existingUser);

            emailService.sendRegistrationEmail(email, registrationToken, registrationRequest.getFrontUrl());
        } else {
            String registrationToken = hashingService.generateRegistrationToken(email);

            User newUser = new User();
            newUser.setEmail(email);
            newUser.setRegistrationToken(registrationToken);
            newUser.setDateRequestRegistrationToken(new Date());
            newUser.setStatus(UserStatus.REGISTRATION_LINK_SENT);

            userRepository.save(newUser);

            emailService.sendRegistrationEmail(email, registrationToken, registrationRequest.getFrontUrl());
        }
    }

    public boolean verifyRegistrationToken(String token) {
        User user = userRepository.findByRegistrationToken(token);
        if (user == null || user.getDateRequestRegistrationToken() == null) {
            return false;
        }

        long threeHoursAgoMillis = System.currentTimeMillis() - registrationTokenLifetimeMillis;

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

        long threeHoursAgoMillis = System.currentTimeMillis() - registrationTokenLifetimeMillis;

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
