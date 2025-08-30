package com.admin_expenses.admin_expenses.domain.exception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String name) {
        super("No role found with name "+name);
    }
    public RoleNotFoundException(Long id) {
        super("No role found with id "+id);
    }
}

