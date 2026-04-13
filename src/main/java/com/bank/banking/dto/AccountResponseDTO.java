package com.bank.banking.dto;

import com.bank.banking.enums.AccountType;
import com.bank.banking.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDTO {

    private UUID accountId;
    private String accountNumber;
    private UUID userId;
    private AccountType accountType;
    private BigDecimal balance;
    private Status status;
    private LocalDateTime createdAt;
}
