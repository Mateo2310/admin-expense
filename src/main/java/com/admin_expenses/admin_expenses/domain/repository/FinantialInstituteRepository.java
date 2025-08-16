package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;

import java.util.List;
import java.util.Optional;

public interface FinantialInstituteRepository {
    Optional<FinantialInstitute> findById(Long id);
    Optional<FinantialInstitute> findByUserId(Long userId);
    FinantialInstitute save(FinantialInstitute finantialInstitute);
    FinantialInstitute update(FinantialInstitute finantialInstitute);
    void delete(FinantialInstitute finantialInstitute);
    void deleteById(Long id);
    List<FinantialInstitute> findAll();
    Optional<FinantialInstitute> findByName(String name);
}
