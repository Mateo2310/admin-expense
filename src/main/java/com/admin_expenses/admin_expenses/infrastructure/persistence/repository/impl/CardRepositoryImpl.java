package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.CardMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.ICardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private final ICardRepository iCardRepository;

    public CardRepositoryImpl(ICardRepository iCardRepositoryr) {
        this.iCardRepository = iCardRepositoryr;
    }

    @Override
    public CardModel findById(Long id) {
        return this.iCardRepository.findById(id)
                .map(CardMapper::toDomainCard).orElse(null);
    }

    @Override
    public CardModel save(CardModel cardModel) {
        CardEntity cardEntity = CardMapper.toCardEntity(cardModel);
        CardEntity savedCardEntity = this.iCardRepository.save(cardEntity);
        return CardMapper.toDomainCard(savedCardEntity);
    }

    @Override
    public CardModel update(CardModel cardModel) {
        return CardMapper.toDomainCard(this.iCardRepository.save(CardMapper.toCardEntity(cardModel)));
    }

    @Override
    public void delete(CardModel cardModel) {
        this.iCardRepository.delete(CardMapper.toCardEntity(cardModel));
    }

    @Override
    public void deleteById(Long id) {
        this.iCardRepository.deleteById(id);
    }

    @Override
    public List<CardModel> findAll() {
        return this.iCardRepository.findAll().stream().map(CardMapper::toDomainCard).collect(Collectors.toList());
    }

    @Override
    public CardModel findByUserId(Long userId) {
        return null;
    }
}
