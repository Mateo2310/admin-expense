package com.admin_expenses.admin_expenses.domain.exception;

public class CardTierNotFoundException extends RuntimeException{
    public CardTierNotFoundException(Long id) {
        super("No Card tier found with id "+id);
    }

    public CardTierNotFoundException(String name) {
        super("No Card tier found with name "+name);
    }
}
