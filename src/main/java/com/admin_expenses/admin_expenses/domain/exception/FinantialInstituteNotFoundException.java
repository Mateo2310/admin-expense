package com.admin_expenses.admin_expenses.domain.exception;

public class FinantialInstituteNotFoundException extends BusinessException{
    public FinantialInstituteNotFoundException(Long id) {
        super("No FinantialInstitute found with id "+id);
    }
    public FinantialInstituteNotFoundException(String name) {
        super("No FinantialInstitute found with name "+name);
    }
}
