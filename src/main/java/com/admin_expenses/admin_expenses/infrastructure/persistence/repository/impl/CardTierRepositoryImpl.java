package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.CardTierModel;
import com.admin_expenses.admin_expenses.domain.repository.CardTierRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.CardTierMapper;
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
    public CardTierModel findById(Long id) {
        return this.iCardTierRepository.findById(id).map(CardTierMapper::toDomainCardTier).orElse(null);
    }

    @Override
    public CardTierModel save(CardTierModel cardTierModel) {
        CardTierEntity cardTierEntity = CardTierMapper.toCardTierEntity(cardTierModel);
        CardTierEntity savedCardTierEntity = this.iCardTierRepository.save(cardTierEntity);
        return CardTierMapper.toDomainCardTier(savedCardTierEntity);
    }

    @Override
    public CardTierModel update(CardTierModel cardTierModel) {
        return CardTierMapper.toDomainCardTier(this.iCardTierRepository.save(CardTierMapper.toCardTierEntity(cardTierModel)));
    }

    @Override
    public void deleteById(Long id) {
        this.iCardTierRepository.deleteById(id);
    }

    @Override
    public CardTierModel findByName(String name) {
        Optional<CardTierEntity> cardTierFinded = this.iCardTierRepository.findCardTierEntitieByName(name);
        return cardTierFinded.map(CardTierMapper::toDomainCardTier).orElse(null);
    }

    @Override
    public List<CardTierModel> findAll() {
        return this.iCardTierRepository.findAll().stream().map(CardTierMapper::toDomainCardTier).collect(Collectors.toList());
    }
}
