package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User findById(Long id);
    User save(User user);
    User update(User user);
    void delete(User user);
    void deleteById(Long id);
    List<User> findAll();
    User findByUsername(String username);
}
