package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.CardTierModel;

import java.util.List;

public interface CardTierRepository {
    CardTierModel findById(Long id, Long userId);
    CardTierModel save(CardTierModel cardTierModel);
    void deleteById(Long id, Long userId);
    List<CardTierModel> findAll(Long userId);
}
