package com.admin_expenses.admin_expenses.infrastructure.persistence.mapper;

import com.admin_expenses.admin_expenses.domain.model.Card;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class CardMapper {
    public static Card toDomainCard(CardEntity cardEntity) {
        User userDomain = UserMapper.toDomainUser(cardEntity.getCreatedBy());
        FinantialInstitute finantialInstituteDomain = FinantialInstituteMapper.toDomainFinantianInstitute(cardEntity.getFinantialInstituteEntity());
        return new Card(cardEntity.getId(), cardEntity.getCardType(), finantialInstituteDomain, cardEntity.getAlias(), cardEntity.getCreatedAt(), cardEntity.getUpdatedAt(), userDomain);
    }

    public static CardEntity toCardEntity(Card card) {
        UserEntity userEntity = UserMapper.toUserEntity(card.getCreatedBy());
        FinantialInstituteEntity finantialInstituteEntity = FinantialInstituteMapper.toFinantialInstitute(card.getFinantialInstitute());
        return new CardEntity(card.getId(),card.getCardType(), finantialInstituteEntity, card.getAlias(), finantialInstituteEntity.getCreatedAt(), finantialInstituteEntity.getUpdatedAt(), userEntity);
    }
}
