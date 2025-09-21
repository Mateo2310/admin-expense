package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;

import java.util.List;

public interface PurchaseRepository {
    PurchaseModel findById(Long id, Long userId);
    PurchaseModel save(PurchaseModel purchaseModel);
    void deleteById(Long id, Long userId);
    List<PurchaseModel> findAll(Long userId);
}
