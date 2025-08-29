package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    Role findById(Long id);
    Role save(Role role);
    Role update(Role role);
    void delete(Role role);
    void deleteById(Long id);
    List<Role> findAll();
    Role findByUserId(Long userId);
    Role findByName(String name);
}
