package com.bank.banking.service;

import com.bank.banking.dto.UserRequestDTO;
import com.bank.banking.dto.UserResponseDTO;
import com.bank.banking.entity.BankAccount;
import com.bank.banking.entity.User;
import com.bank.banking.exception.UserFoundException;
import com.bank.banking.exception.UserNotFoundException;
import com.bank.banking.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public UserResponseDTO mapToResponse(User user){

        UserResponseDTO response = new UserResponseDTO();
        response.setUserId(user.getUserId());
        response.setUserName(user.getUserName());
        response.setUserEmail(user.getUserEmail());
        response.setUserPhoneNumber(user.getUserPhoneNumber());
        response.setCreatedAt(user.getCreatedAt());

        return response;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequest) {

        Optional<User> fetch = userRepository.findByUserEmail(userRequest.getUserEmail());
        if(fetch.isPresent()){
            throw new UserFoundException("This email is already Registered.Please Login!!");
        }

        User user = new User();
        user.setUserName(userRequest.getUserName());
        user.setUserEmail(userRequest.getUserEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUserPhoneNumber(userRequest.getUserPhoneNumber());

        User saved = userRepository.save(user);
        return mapToResponse(saved);
    }

    @Override
    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found.Please Register!!"));

        return mapToResponse(user);
    }
}
