package com.bank.banking.dto;

import com.bank.banking.enums.TransactionStatus;
import com.bank.banking.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private UUID transactionId;
    private BigDecimal amount;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private UUID fromAccountId;
    private UUID toAccountId;
    private LocalDateTime timestamp;
}
