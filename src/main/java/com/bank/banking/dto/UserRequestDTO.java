package com.bank.banking.dto;

import com.bank.banking.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String userName;
    private String userEmail;
    private String password;
    private Role role;
    private String userPhoneNumber;
}
