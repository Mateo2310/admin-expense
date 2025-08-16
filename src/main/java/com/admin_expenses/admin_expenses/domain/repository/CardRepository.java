package com.admin_expenses.admin_expenses.domain.repository;

import com.admin_expenses.admin_expenses.domain.model.Card;

import java.util.List;
import java.util.Optional;

public interface CardRepository {
    Optional<Card> findById(Long id);
    Card save(Card card);
    Card update(Card card);
    void delete(Card card);
    void deleteById(Long id);
    List<Card> findAll();
    Optional<Card> findByUserId(Long userId);
}
