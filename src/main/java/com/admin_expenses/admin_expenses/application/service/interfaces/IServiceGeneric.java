package com.admin_expenses.admin_expenses.application.service.interfaces;

import java.util.List;

public interface IServiceGeneric<T, D> {
    String create(D dto);
    String delete(String name);
    String update(D dto);
    String deleteById(Long id);
    T findById(Long id);
    List<T> findAll();
}
