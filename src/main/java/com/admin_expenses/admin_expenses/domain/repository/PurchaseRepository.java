package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.Purchase;

import java.util.List;
import java.util.Optional;

public interface PurchaseRepository {
    Purchase findById(Long id);
    Purchase save(Purchase purchase);
    Purchase update(Purchase purchase);
    void delete(Purchase purchase);
    void deleteById(Long id);
    List<Purchase> findAll();
    Purchase findByUserId(Long userId);
}
