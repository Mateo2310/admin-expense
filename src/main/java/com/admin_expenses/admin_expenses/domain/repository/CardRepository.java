package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.CardModel;

import java.util.List;

public interface CardRepository {
    CardModel findById(Long id, Long userId);
    CardModel save(CardModel cardModel);
    void deleteById(Long id, Long userId);
    List<CardModel> findAll(Long userId);
}
