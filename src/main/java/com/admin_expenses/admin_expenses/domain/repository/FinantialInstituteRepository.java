package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;

import java.util.List;

public interface FinantialInstituteRepository {
    FinantialInstituteModel findById(Long id);
    FinantialInstituteModel findByUserId(Long userId);
    FinantialInstituteModel save(FinantialInstituteModel finantialInstituteModel);
    FinantialInstituteModel update(FinantialInstituteModel finantialInstituteModel);
    void delete(FinantialInstituteModel finantialInstituteModel);
    void deleteById(Long id);
    List<FinantialInstituteModel> findAll();
    FinantialInstituteModel findByName(String name);
}
