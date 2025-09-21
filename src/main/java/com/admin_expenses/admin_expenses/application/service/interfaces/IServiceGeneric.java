package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.domain.model.UserModel;

import java.util.List;

public interface IServiceGeneric<T, D> {
    String create(D dto, UserModel userModel);
    String deleteById(Long id, Long userId);
    T findById(Long id, Long userId);
    List<T> findAll(Long userId);
}
