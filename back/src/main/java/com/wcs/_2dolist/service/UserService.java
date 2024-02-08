package com.wcs._2dolist.service;

import com.wcs._2dolist.dto.UserDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.enums.UserStatus;
import com.wcs._2dolist.mapper.UserMapper;
import com.wcs._2dolist.repository.UserRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }

    public List<UserDTO> getAllUsers() {

        return userRepository.findAll().stream()
                .filter(user -> user.getStatus() == UserStatus.ACTIVE)
                .map(userMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .filter(u -> u.getStatus() == UserStatus.ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("Active user not found with id: " + id));
        return userMapper.convertToDTO(user);
    }

    public UserDTO createUser(UserDTO userDTO) {
        String email = userDTO.getEmail();

        User existingUser = userRepository.findByEmail(email);

        if (existingUser == null) {
            User user = userMapper.convertToEntity(userDTO);
            user.setStatus(UserStatus.ACTIVE);
            return userMapper.convertToDTO(userRepository.save(user));
        } else {
            if (existingUser.getStatus() == UserStatus.DELETED) {
                // Email exists in DB and UserStatus is DELETED -> overwrite old user data by new user data
                User user = userMapper.convertToEntity(userDTO);
                user.setId(existingUser.getId());
                user.setStatus(UserStatus.ACTIVE);
                return userMapper.convertToDTO(userRepository.save(user));
            } else {
                throw new IllegalStateException("A user with this email already exists and is active or blocked");
            }
        }
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (existingUser.getStatus() != UserStatus.ACTIVE) {
            throw new IllegalStateException("User is not active and cannot be updated");
        }

        User user = userMapper.convertToEntity(userDTO);
        user.setId(id);
        user.setStatus(UserStatus.ACTIVE);
        return userMapper.convertToDTO(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        user.setStatus(UserStatus.DELETED);
        userRepository.save(user);
    }
}
