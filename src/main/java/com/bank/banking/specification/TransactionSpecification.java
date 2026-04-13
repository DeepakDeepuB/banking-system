package com.bank.banking.specification;

import com.bank.banking.entity.Transaction;
import com.bank.banking.enums.TransactionType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.UUID;

public class TransactionSpecification {

    public static Specification<Transaction> hasAccount(UUID accountId){
        return (root, query, cb) -> cb.or(
                cb.equal(root.get("fromAccount").get("bankAccountId"), accountId),
                cb.equal(root.get("toAccount").get("bankAccountId"), accountId));
    }

    public static Specification<Transaction> hasType(TransactionType type){
        return (root, query, cb) ->
                cb.equal(root.get("transactionType"), type);
    }

    public static Specification<Transaction> betweenDates(LocalDateTime from, LocalDateTime to){
        return ((root, query, cb) ->
                cb.between(root.get("timestamp"), from, to));
    }
}
