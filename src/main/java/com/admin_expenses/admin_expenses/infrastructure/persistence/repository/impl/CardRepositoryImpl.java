package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.CardMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.ICardRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CardRepositoryImpl implements CardRepository {
    private final ICardRepository iCardRepository;

    @Override
    public CardModel findById(Long id, Long userId) {
        return this.iCardRepository.findByIdAndUserId(id, userId)
                .map(CardMapper::toDomainCard).orElse(null);
    }

    @Override
    public CardModel save(CardModel cardModel) {
        CardEntity cardEntity = CardMapper.toCardEntity(cardModel);
        CardEntity savedCardEntity = this.iCardRepository.save(cardEntity);
        return CardMapper.toDomainCard(savedCardEntity);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        this.iCardRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public List<CardModel> findAll(Long userId) {
        return this.iCardRepository.findAllByUserId(userId).stream().map(CardMapper::toDomainCard).collect(Collectors.toList());
    }
}
