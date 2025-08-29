package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces;

import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICardTierRepository extends JpaRepository<CardTierEntity, Long> {
    Optional<CardTierEntity> findCardTierEntitieByName(String name);
}
