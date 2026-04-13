package com.bank.banking.service;

import com.bank.banking.dto.*;

import java.util.UUID;

public interface BankAccountService {

    AccountResponseDTO createAccount(AccountRequestDTO accountRequest);
    AccountResponseDTO findByAccountId(UUID accountId);
    AccountResponseDTO deposit(DepositRequestDTO depositRequest);
    AccountResponseDTO withdraw(WithdrawRequestDTO withdrawRequest);
    AccountResponseDTO transfer(TransferRequestDTO transferRequest);
}
