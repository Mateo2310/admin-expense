package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.CardTier;
import com.admin_expenses.admin_expenses.domain.repository.CardTierRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.mapper.CardTierMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.ICardTierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CardTierRepositoryImpl implements CardTierRepository {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private final ICardTierRepository iCardTierRepository;

    public CardTierRepositoryImpl(ICardTierRepository iCardTierRepository) {
        this.iCardTierRepository = iCardTierRepository;
    }

    @Override
    public Optional<CardTier> findById(Long id) {
        return this.iCardTierRepository.findById(id).map(CardTierMapper::toDomainCardTier);
    }

    @Override
    public CardTier save(CardTier cardTier) {
        CardTierEntity cardTierEntity = CardTierMapper.toCardTierEntity(cardTier);
        CardTierEntity savedCardTierEntity = this.iCardTierRepository.save(cardTierEntity);
        return CardTierMapper.toDomainCardTier(savedCardTierEntity);
    }

    @Override
    public CardTier update(CardTier cardTier) {
        return CardTierMapper.toDomainCardTier(this.iCardTierRepository.save(CardTierMapper.toCardTierEntity(cardTier)));
    }

    @Override
    public void deleteById(Long id) {
        this.iCardTierRepository.deleteById(id);
    }

    @Override
    public Optional<CardTier> findByName(String name) {
        Optional<CardTierEntity> cardTierFinded = this.iCardTierRepository.findCardTierEntitieByName(name);
        return cardTierFinded.map(CardTierMapper::toDomainCardTier);
    }

    @Override
    public List<CardTier> findAll() {
        return this.iCardTierRepository.findAll().stream().map(CardTierMapper::toDomainCardTier).collect(Collectors.toList());
    }
}
