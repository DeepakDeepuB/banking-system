package com.bank.banking.entity;

import com.bank.banking.enums.TransactionStatus;
import com.bank.banking.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID transactionId;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "transaction_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @ManyToOne
    @JoinColumn(name = "from_account")
    private BankAccount fromAccount;

    @ManyToOne
    @JoinColumn(name = "to_account")
    private BankAccount toAccount;

    @Column(name = "created_at",nullable = false)
    private LocalDateTime timestamp;

    @PrePersist
    public void prePresist(){
        this.timestamp = LocalDateTime.now();
    }
}
