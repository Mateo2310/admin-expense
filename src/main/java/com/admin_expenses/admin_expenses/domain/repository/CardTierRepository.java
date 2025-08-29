package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.CardTier;

import java.util.List;
import java.util.Optional;

public interface CardTierRepository {
    CardTier findById(Long id);
    CardTier save(CardTier cardTier);
    CardTier update(CardTier cardTier);
    void deleteById(Long id);
    CardTier findByName(String name);
    List<CardTier> findAll();
}
