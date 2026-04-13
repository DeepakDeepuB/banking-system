package com.bank.banking.service;

import com.bank.banking.dto.PageResponseDTO;
import com.bank.banking.dto.TransactionResponseDTO;
import com.bank.banking.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TransactionService {

    PageResponseDTO<TransactionResponseDTO> getAllTransactions(UUID accountId, Pageable page);
}
