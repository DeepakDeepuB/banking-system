package com.bank.banking.service;

import com.bank.banking.dto.UserRequestDTO;
import com.bank.banking.dto.UserResponseDTO;

import java.util.UUID;


public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequest);

    UserResponseDTO findById(UUID id);
}
