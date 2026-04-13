package com.bank.banking.controller;

import com.bank.banking.dto.UserRequestDTO;
import com.bank.banking.dto.UserResponseDTO;
import com.bank.banking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userRequest){
        UserResponseDTO response = userService.createUser(userRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> fetchUserById(@PathVariable UUID id){
        UserResponseDTO response = userService.findById(id);
        return ResponseEntity.ok(response);
    }
}
