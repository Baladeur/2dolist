package com.wcs._2dolist.controller;

import com.wcs._2dolist.dto.UserDTO;
import com.wcs._2dolist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // Test with Postman
    // POST http://localhost:8080/users
    // Body > raw > JSON
    //{
    //    "email": "alice.smith@example.com9",
    //        "nickName": "alice_s",
    //        "firstName": "Alice",
    //        "lastName": "Smith",
    //        "picture": "https://example.com/profiles/alicesmith.jpg",
    //        "address": "789 Oak Street, Smalltown, Canada",
    //        "accountStatus": "ACTIVE",
    //        "emailVerified": true,
    //        "lastUpdated": "2024-01-29T08:45:00Z",
    //        "role": "ADMIN",
    //        "passwordHash": "your_password_hash",
    //        "registrationUrlHash": "your_registration_url_hash",
    //        "dateRequestRegistrationUrlHash": "2024-01-29"
    //}
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
