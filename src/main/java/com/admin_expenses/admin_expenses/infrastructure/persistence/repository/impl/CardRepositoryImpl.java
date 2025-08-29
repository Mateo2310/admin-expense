package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.Card;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.mapper.CardMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.ICardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CardRepositoryImpl implements CardRepository {
    private final ICardRepository iCardRepository;

    public CardRepositoryImpl(ICardRepository iCardRepositoryr) {
        this.iCardRepository = iCardRepositoryr;
    }

    @Override
    public Card findById(Long id) {
        return this.iCardRepository.findById(id)
                .map(CardMapper::toDomainCard).orElse(null);
    }

    @Override
    public Card save(Card card) {
        CardEntity cardEntity = CardMapper.toCardEntity(card);
        CardEntity savedCardEntity = this.iCardRepository.save(cardEntity);
        return CardMapper.toDomainCard(savedCardEntity);
    }

    @Override
    public Card update(Card card) {
        return CardMapper.toDomainCard(this.iCardRepository.save(CardMapper.toCardEntity(card)));
    }

    @Override
    public void delete(Card card) {
        this.iCardRepository.delete(CardMapper.toCardEntity(card));
    }

    @Override
    public void deleteById(Long id) {
        this.iCardRepository.deleteById(id);
    }

    @Override
    public List<Card> findAll() {
        return this.iCardRepository.findAll().stream().map(CardMapper::toDomainCard).collect(Collectors.toList());
    }

    @Override
    public Card findByUserId(Long userId) {
        return null;
    }
}
