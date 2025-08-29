package com.admin_expenses.admin_expenses.domain.exception;

public class CardNotFoundException extends BusinessException{
    public CardNotFoundException(Long id) {
        super("No Card found with id "+id);
    }
}
