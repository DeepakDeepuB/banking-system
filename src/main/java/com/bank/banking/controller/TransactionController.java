package com.bank.banking.controller;

import com.bank.banking.dto.PageResponseDTO;
import com.bank.banking.dto.TransactionResponseDTO;
import com.bank.banking.enums.TransactionType;
import com.bank.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/transactions/{accountId}")
    public ResponseEntity<PageResponseDTO<TransactionResponseDTO>> fetchAllTransactions(@PathVariable UUID accountId,
                                                                                        @RequestParam(defaultValue = "0") int page,
                                                                                        @RequestParam(defaultValue = "5") int size,
                                                                                        @RequestParam(required = false) TransactionType type,
                                                                                        @RequestParam(defaultValue = "desc") String sort){

        Sort sorting = sort.equalsIgnoreCase("desc") ?
                Sort.by("timestamp").descending() :
                Sort.by("timestamp").ascending();

        Pageable pageable = PageRequest.of(page,size, sorting);

        PageResponseDTO<TransactionResponseDTO> response = transactionService.getAllTransactions(accountId, type, pageable);
        return ResponseEntity.ok(response);
    }
}
