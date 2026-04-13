package com.bank.banking.exception;

import jakarta.persistence.Id;

public class InvalidAmountException extends RuntimeException{

    public InvalidAmountException(String message){
        super(message);
    }
}
