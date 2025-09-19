package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.CardTierModel;

import java.util.List;

public interface CardTierRepository {
    CardTierModel findById(Long id);
    CardTierModel save(CardTierModel cardTierModel);
    CardTierModel update(CardTierModel cardTierModel);
    void deleteById(Long id);
    CardTierModel findByName(String name);
    List<CardTierModel> findAll();
}
