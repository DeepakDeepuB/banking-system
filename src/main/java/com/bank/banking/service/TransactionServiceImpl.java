package com.bank.banking.service;

import com.bank.banking.dto.PageResponseDTO;
import com.bank.banking.dto.TransactionResponseDTO;
import com.bank.banking.entity.Transaction;
import com.bank.banking.enums.TransactionType;
import com.bank.banking.exception.InvalidDateRangeException;
import com.bank.banking.respository.TransactionRepository;
import com.bank.banking.specification.TransactionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public PageResponseDTO<TransactionResponseDTO> getAllTransactions(UUID accountId, TransactionType type, LocalDateTime fromDate,
                                                                      LocalDateTime toDate, Pageable page) {

        Specification<Transaction> spec = Specification.where(
                TransactionSpecification.hasAccount(accountId));

        if(type != null){
            spec = spec.and(TransactionSpecification.hasType(type));
        }

        if(fromDate != null && toDate != null && fromDate.isAfter(toDate)){
            throw new InvalidDateRangeException("FromDate cannot be greater than ToDate");
        }

        if(fromDate != null && toDate != null){
            spec = spec.and(TransactionSpecification.betweenDates(fromDate, toDate));
        }

        Page<Transaction> transactions = transactionRepository.findAll(spec, page);

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
