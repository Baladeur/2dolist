package com.wcs._2dolist.mapper;

import com.wcs._2dolist.dto.UserDTO;
import com.wcs._2dolist.entity.User;
import com.wcs._2dolist.enums.UserRole;
import com.wcs._2dolist.enums.UserStatus;
import org.springframework.stereotype.Service;


@Service
public class UserMapper {

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setNickName(userDTO.getNickName());
        user.setPicture(userDTO.getPicture());
        user.setAddress(userDTO.getAddress());
        if (userDTO.getStatus() == null) {
            user.setStatus(UserStatus.ACTIVE);
        } else {
            user.setStatus(UserStatus.valueOf(userDTO.getStatus().toUpperCase()));
        }
        user.setEmailVerified(userDTO.isEmailVerified());
        user.setLastUpdated(userDTO.getLastUpdated());
        user.setRole(UserRole.valueOf(userDTO.getRole().toUpperCase()));
        return user;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setNickName(user.getNickName());
        userDTO.setPicture(user.getPicture());
        userDTO.setAddress(user.getAddress());
        userDTO.setStatus(user.getStatus().name());

        userDTO.setEmailVerified(user.isEmailVerified());
        userDTO.setLastUpdated(user.getLastUpdated());
        userDTO.setRole(user.getRole().name());
        return userDTO;
    }
}