package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.CardModel;

import java.util.List;

public interface CardRepository {
    CardModel findById(Long id);
    CardModel save(CardModel cardModel);
    CardModel update(CardModel cardModel);
    void delete(CardModel cardModel);
    void deleteById(Long id);
    List<CardModel> findAll();
    CardModel findByUserId(Long userId);
}
