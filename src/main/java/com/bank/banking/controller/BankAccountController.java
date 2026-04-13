package com.bank.banking.controller;

import com.bank.banking.dto.*;
import com.bank.banking.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @PostMapping("/accounts")
    public ResponseEntity<AccountResponseDTO> createBankAccount(@RequestBody AccountRequestDTO accountRequest){
        AccountResponseDTO response = bankAccountService.createAccount(accountRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountResponseDTO> fetchByBankAccountId(@PathVariable UUID id){
        AccountResponseDTO response = bankAccountService.findByAccountId(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/accounts/deposit")
    public ResponseEntity<AccountResponseDTO> deposit(@RequestBody DepositRequestDTO request){
        AccountResponseDTO response = bankAccountService.deposit(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/accounts/withdraw")
    public ResponseEntity<AccountResponseDTO> withdraw(@RequestBody WithdrawRequestDTO request){
        AccountResponseDTO response = bankAccountService.withdraw(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/accounts/transfer")
    public ResponseEntity<AccountResponseDTO> transfer(@RequestBody TransferRequestDTO transferRequest){
        AccountResponseDTO response = bankAccountService.transfer(transferRequest);
        return ResponseEntity.ok(response);
    }
}
