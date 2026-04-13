package com.bank.banking.service;

import com.bank.banking.dto.PageResponseDTO;
import com.bank.banking.dto.TransactionResponseDTO;
import com.bank.banking.entity.Transaction;
import com.bank.banking.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionService {

    PageResponseDTO<TransactionResponseDTO> getAllTransactions(UUID accountId, TransactionType type, LocalDateTime fromDate,
                                                               LocalDateTime toDate, Pageable page);
}
