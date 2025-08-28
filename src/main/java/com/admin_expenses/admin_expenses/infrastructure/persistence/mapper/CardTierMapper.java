package com.admin_expenses.admin_expenses.infrastructure.persistence.mapper;

import com.admin_expenses.admin_expenses.domain.model.CardTier;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class CardTierMapper {
    public static CardTier toDomainCardTier(CardTierEntity cardTierEntity) {
        User userDomain = UserMapper.toDomainUser(cardTierEntity.getCreatedBy());
        return new CardTier(cardTierEntity.getId(), cardTierEntity.getName(), cardTierEntity.getIcon(), cardTierEntity.getCreatedAt(), cardTierEntity.getUpdatedAt(), userDomain);
    }

    public static CardTierEntity toCardTierEntity(CardTier cardTier) {
        UserEntity userEntity = UserMapper.toUserEntity(cardTier.getCreatedBy());
        return new CardTierEntity(cardTier.getId(), cardTier.getName(), cardTier.getIcon(), cardTier.getCreatedAt(), cardTier.getUpdatedAt(), userEntity);
    }
}
