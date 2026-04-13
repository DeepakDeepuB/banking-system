package com.bank.banking.service;

import com.bank.banking.dto.*;
import com.bank.banking.entity.BankAccount;
import com.bank.banking.entity.Transaction;
import com.bank.banking.entity.User;
import com.bank.banking.enums.Status;
import com.bank.banking.enums.TransactionStatus;
import com.bank.banking.enums.TransactionType;
import com.bank.banking.exception.AccountNotFoundException;
import com.bank.banking.exception.InsufficientFundException;
import com.bank.banking.exception.InvalidAmountException;
import com.bank.banking.exception.UserNotFoundException;
import com.bank.banking.respository.BankAccountRepository;
import com.bank.banking.respository.TransactionRepository;
import com.bank.banking.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.UUID;

@Service
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    BankAccountRepository bankAccountRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public String generateAccountNumber(){
        SecureRandom random = new SecureRandom();
        long number = 1000000000L + (long) (random.nextDouble() * 90000000000L);

        return "ACC" + number;
    }

    public AccountResponseDTO mapToResponse(BankAccount account){
        AccountResponseDTO response = new AccountResponseDTO();
        response.setAccountId(account.getBankAccountId());
        response.setAccountNumber(account.getAccountNumber());
        response.setUserId(account.getUser().getUserId());
        response.setAccountType(account.getAccountType());
        response.setBalance(account.getBalance());
        response.setStatus(account.getStatus());
        response.setCreatedAt(account.getCreatedAt());

        return response;
    }

    @Override
    public AccountResponseDTO createAccount(AccountRequestDTO accountRequest) {
        User user = userRepository.findById(accountRequest.getUserId()).orElseThrow(
                () -> new UserNotFoundException("User not found.Please Register!!")
        );

        BankAccount account = new BankAccount();
        String accountNumber;
        do {
            accountNumber = generateAccountNumber();
        }while (bankAccountRepository.existsByAccountNumber(accountNumber));
        account.setAccountNumber(accountNumber);
        account.setUser(user);
        account.setBalance(BigDecimal.ZERO);
        account.setAccountType(accountRequest.getAccountType());
        account.setStatus(Status.ACTIVE);

        BankAccount saved = bankAccountRepository.save(account);

        return mapToResponse(saved);
    }

    @Override
    public AccountResponseDTO findByAccountId(UUID accountId) {
        BankAccount account = bankAccountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException("BankAccount not found. Please enter correct ID!!")
        );

        return mapToResponse(account);
    }

    @Transactional
    @Override
    public AccountResponseDTO deposit(DepositRequestDTO depositRequest) {
        BankAccount account = bankAccountRepository.findById(depositRequest.getAccountId()).orElseThrow(
                () -> new AccountNotFoundException("BankAccount not found. Please enter correct ID!!")
        );

        if(depositRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException("Amount which is entered should be greater than zero!");
        }

        account.setBalance(account.getBalance().add(depositRequest.getAmount()));

        Transaction depositTrans = new Transaction();
        depositTrans.setAmount(depositRequest.getAmount());
        depositTrans.setTransactionType(TransactionType.DEPOSIT);
        depositTrans.setTransactionStatus(TransactionStatus.SUCCESS);
        depositTrans.setToAccount(account);

        BankAccount saved = bankAccountRepository.save(account);
        transactionRepository.save(depositTrans);

        return mapToResponse(saved);
    }

    @Transactional
    @Override
    public AccountResponseDTO withdraw(WithdrawRequestDTO withdrawRequest) {
        BankAccount account = bankAccountRepository.findById(withdrawRequest.getAccountId()).orElseThrow(
                () -> new AccountNotFoundException("BankAccount not found. Please enter correct ID!!")
        );

        if (withdrawRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0){
            throw new InvalidAmountException("Amount which is entered should be greater than zero!");
        }

        if (account.getBalance().compareTo(withdrawRequest.getAmount()) < 0){
            throw new InsufficientFundException("Insufficient Balance!!");
        }

        account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));

        Transaction withdrawTrans = new Transaction();
        withdrawTrans.setAmount(withdrawRequest.getAmount());
        withdrawTrans.setTransactionType(TransactionType.WITHDRAW);
        withdrawTrans.setTransactionStatus(TransactionStatus.SUCCESS);
        withdrawTrans.setFromAccount(account);

        transactionRepository.save(withdrawTrans);
        BankAccount saved = bankAccountRepository.save(account);
        return mapToResponse(saved);
    }

    @Transactional
    @Override
    public AccountResponseDTO transfer(TransferRequestDTO transferRequest) {
//        BankAccount fromAccount = bankAccountRepository.findById(transferRequest.getFromAccountId())
//                .orElseThrow(() -> new AccountNotFoundException("Sender Account Not Found"));
//
//        BankAccount toAccount = bankAccountRepository.findById(transferRequest.getToAccountId())
//                .orElseThrow(() -> new AccountNotFoundException("Receiver Account Not Found"));

        // Used below lines because used lock for simultaneous transfer seafty

        BankAccount fromAccount = bankAccountRepository.findByIdForUpdate(transferRequest.getFromAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Sender Account Not Found"));

        BankAccount toAccount = bankAccountRepository.findByIdForUpdate(transferRequest.getToAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Receiver Account Not Found"));

        if(transferRequest.getAmount().compareTo(BigDecimal.ZERO) <=0){
            throw new InvalidAmountException("Amount which is entered should be greater than zero!");
        }


        if(fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0){
            throw new InsufficientFundException("Insufficient Balance!!");
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));

        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));

        Transaction transferTrans = new Transaction();
        transferTrans.setAmount(transferRequest.getAmount());
        transferTrans.setTransactionType(TransactionType.TRANSFER);
        transferTrans.setTransactionStatus(TransactionStatus.SUCCESS);
        transferTrans.setFromAccount(fromAccount);
        transferTrans.setToAccount(toAccount);

//        Commented below lines because we are using @Transactional
//        bankAccountRepository.save(fromAccount);
//        bankAccountRepository.save(toAccount);

        transactionRepository.save(transferTrans);
        return mapToResponse(fromAccount);
    }
}
