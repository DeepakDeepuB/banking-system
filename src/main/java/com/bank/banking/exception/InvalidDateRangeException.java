package com.bank.banking.exception;

public class InvalidDateRangeException extends RuntimeException{

    public InvalidDateRangeException(String message){
        super(message);
    }
}
