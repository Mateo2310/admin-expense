package com.admin_expenses.admin_expenses.domain.exception;

public class UserNotFoundException extends BusinessException{
    public UserNotFoundException(Long id) {
        super("No User found with id "+id);
    }

    public UserNotFoundException(String username) {
        super("No User found with username "+username);
    }
}
