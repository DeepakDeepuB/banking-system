package com.bank.banking.respository;

import com.bank.banking.entity.Transaction;
import com.bank.banking.enums.TransactionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID>, JpaSpecificationExecutor<Transaction> {

//    Page<Transaction> findByFromAccount_BankAccountIdOrToAccount_BankAccountId(UUID fromAccountId, UUID toAccountId, Pageable pageable);
//
//    Page<Transaction> findByTransactionTypeAndFromAccount_BankAccountIdOrTransactionTypeAndToAccount_BankAccountId(TransactionType type1,
//                                                                                                                   UUID fromAccountId,
//                                                                                                                   TransactionType type2,
//                                                                                                                   UUID toAccountId,
//                                                                                                                   Pageable pageable);
//
//    Page<Transaction> findByTransactionTypeAndTimestampBetweenAndFromAccount_BankAccountIdOrTransactionTypeAndTimestampBetweenAndToAccount_BankAccountId(
//            TransactionType type1,
//            LocalDateTime fromDate1,
//            LocalDateTime toDate1,
//            UUID fromAccountId,
//
//            TransactionType type2,
//            LocalDateTime fromDate2,
//            LocalDateTime toDate2,
//            UUID toAccountId,
//
//            Pageable pageable
//    );
//
//    Page<Transaction> findByTimestampBetweenAndFromAccount_BankAccountIdOrTimestampBetweenAndToAccount_BankAccountId(
//            LocalDateTime fromDate,
//            LocalDateTime toDate,
//            UUID fromAccountId,
//
//            LocalDateTime fromDate2,
//            LocalDateTime toDate2,
//            UUID toAccountId,
//
//            Pageable pageable
//    );
}
