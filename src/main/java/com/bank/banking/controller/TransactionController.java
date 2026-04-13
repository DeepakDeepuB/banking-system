package com.bank.banking.controller;

import com.bank.banking.dto.PageResponseDTO;
import com.bank.banking.dto.TransactionResponseDTO;
import com.bank.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
                                                                                        @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page,size, Sort.by("timestamp").descending());
        PageResponseDTO<TransactionResponseDTO> response = transactionService.getAllTransactions(accountId, pageable);
        return ResponseEntity.ok(response);
    }
}
