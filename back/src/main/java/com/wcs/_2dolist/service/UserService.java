package com.wcs._2dolist.service;

import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.enums.UserStatus;
import com.wcs._2dolist.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {

        return userRepository.findAll().stream()
                .filter(user -> user.getAccountStatus() == UserStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .filter(user -> user.getAccountStatus() == UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Active user not found with id: " + id));
    }

    public User createUser(User user) {
        String email = user.getEmail();

        User existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {
            return userRepository.save(user);
        } else {
            if (existingUser.getAccountStatus() == UserStatus.DELETED) {
                // Email exists in DB and UserStatus is DELETED -> overwrite old user data by new user data
                user.setId(existingUser.getId());
                return userRepository.save(user);
            } else {
                throw new IllegalStateException("A user with this email already exists and is active or blocked");
            }
        }
    }

    public User updateUser(Long id, User user) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (existingUser.getAccountStatus() != UserStatus.ACTIVE) {
            throw new IllegalStateException("User is not active and cannot be updated");
        }

        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setAccountStatus(UserStatus.DELETED);
        userRepository.save(user);
    }
}

