package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;

import java.util.List;

public interface FinantialInstituteRepository {
    FinantialInstitute findById(Long id);
    FinantialInstitute findByUserId(Long userId);
    FinantialInstitute save(FinantialInstitute finantialInstitute);
    FinantialInstitute update(FinantialInstitute finantialInstitute);
    void delete(FinantialInstitute finantialInstitute);
    void deleteById(Long id);
    List<FinantialInstitute> findAll();
    FinantialInstitute findByName(String name);
}
