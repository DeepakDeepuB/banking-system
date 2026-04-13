package com.bank.banking.respository;

import com.bank.banking.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    Page<Transaction> findByFromAccount_BankAccountIdOrToAccount_BankAccountId(UUID fromAccountId, UUID toAccountId, Pageable pageable);
}
