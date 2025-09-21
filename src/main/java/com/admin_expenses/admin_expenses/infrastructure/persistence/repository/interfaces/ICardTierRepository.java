package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces;

import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICardTierRepository extends JpaRepository<CardTierEntity, Long> {
    Optional<CardTierEntity> findCardTierEntitieByName(String name);
    Optional<CardTierEntity> findCardTierByIdAndUserId(Long id, Long userId);
    List<CardTierEntity> findAllByUserId(Long userId);
    void deleteByIdAndUserId(Long id, Long userId);
}
