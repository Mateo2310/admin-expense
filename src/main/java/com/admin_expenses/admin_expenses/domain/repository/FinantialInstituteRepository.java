package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;

import java.util.List;

public interface FinantialInstituteRepository {
    FinantialInstituteModel findById(Long id, Long userId);
    FinantialInstituteModel save(FinantialInstituteModel finantialInstituteModel);
    void deleteById(Long id, Long userId);
    List<FinantialInstituteModel> findAll(Long userId);
}
