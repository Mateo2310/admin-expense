package com.admin_expenses.admin_expenses.domain.exception;

public class UnexpectedException extends RuntimeException{
    public UnexpectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
