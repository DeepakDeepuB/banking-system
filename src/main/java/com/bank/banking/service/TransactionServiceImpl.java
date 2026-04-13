package com.bank.banking.service;

import com.bank.banking.dto.PageResponseDTO;
import com.bank.banking.dto.TransactionResponseDTO;
import com.bank.banking.entity.Transaction;
import com.bank.banking.respository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public PageResponseDTO<TransactionResponseDTO> getAllTransactions(UUID accountId, Pageable page) {
        Page<Transaction> transactions = transactionRepository.findByFromAccount_BankAccountIdOrToAccount_BankAccountId(accountId,accountId,page);

        Page<TransactionResponseDTO> dto = transactions.map(transaction -> {

            TransactionResponseDTO response = new TransactionResponseDTO();

            response.setTransactionId(transaction.getTransactionId());
            response.setAmount(transaction.getAmount());
            response.setTransactionType(transaction.getTransactionType());
            response.setTransactionStatus(transaction.getTransactionStatus());

            if (transaction.getFromAccount() != null){
                response.setFromAccountId(transaction.getFromAccount().getBankAccountId());
            }

            if (transaction.getToAccount() != null){
                response.setToAccountId(transaction.getToAccount().getBankAccountId());
            }

            response.setTimestamp(transaction.getTimestamp());

            return response;
        });

        PageResponseDTO<TransactionResponseDTO> response = new PageResponseDTO<>();
        response.setContent(dto.getContent());
        response.setPage(dto.getNumber());
        response.setSize(dto.getSize());
        response.setTotalElements(dto.getTotalElements());
        response.setTotalPages(dto.getTotalPages());
        response.setLast(dto.isLast());

        return response;
    }
}
