package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;

import java.util.List;

public interface PurchaseRepository {
    PurchaseModel findById(Long id);
    PurchaseModel save(PurchaseModel purchaseModel);
    PurchaseModel update(PurchaseModel purchaseModel);
    void delete(PurchaseModel purchaseModel);
    void deleteById(Long id);
    List<PurchaseModel> findAll();
    PurchaseModel findByUserId(Long userId);
}
