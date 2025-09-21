package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.CardTierModel;
import com.admin_expenses.admin_expenses.domain.repository.CardTierRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.CardTierMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.ICardTierRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CardTierRepositoryImpl implements CardTierRepository {
    private final ICardTierRepository iCardTierRepository;

    @Override
    public CardTierModel findById(Long id, Long userId) {
        return this.iCardTierRepository.findCardTierByIdAndUserId(id, userId).map(CardTierMapper::toDomainCardTier).orElse(null);
    }

    @Override
    public CardTierModel save(CardTierModel cardTierModel) {
        CardTierEntity cardTierEntity = CardTierMapper.toCardTierEntity(cardTierModel);
        CardTierEntity savedCardTierEntity = this.iCardTierRepository.save(cardTierEntity);
        return CardTierMapper.toDomainCardTier(savedCardTierEntity);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        this.iCardTierRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public List<CardTierModel> findAll(Long userId) {
        return this.iCardTierRepository.findAllByUserId(userId).stream().map(CardTierMapper::toDomainCardTier).collect(Collectors.toList());
    }
}
