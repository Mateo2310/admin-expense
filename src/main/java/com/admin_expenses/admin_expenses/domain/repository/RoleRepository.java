package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.RoleModel;

import java.util.List;

public interface RoleRepository {
    RoleModel findById(Long id);
    RoleModel save(RoleModel roleModel);
    void deleteById(Long id);
    List<RoleModel> findAll();
}
