package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces;

import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICardRepository extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity> findByIdAndUserId(Long id, Long userId);
    List<CardEntity> findAllByUserId(Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
