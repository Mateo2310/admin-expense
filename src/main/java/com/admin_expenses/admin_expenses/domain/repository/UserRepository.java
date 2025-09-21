package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.UserModel;

import java.util.List;

public interface UserRepository {
    UserModel findById(Long id);
    UserModel save(UserModel userModel);
    void deleteById(Long id);
    List<UserModel> findAll();
    UserModel findByUsername(String username);
}
