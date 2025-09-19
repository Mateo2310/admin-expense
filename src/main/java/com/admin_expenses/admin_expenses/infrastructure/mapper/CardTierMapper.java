package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.domain.model.CardTierModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class CardTierMapper {
    public static CardTierModel toDomainCardTier(CardTierEntity cardTierEntity) {
        UserModel userModelDomain = UserMapper.toDomainUser(cardTierEntity.getCreatedBy());
        return new CardTierModel(cardTierEntity.getId(), cardTierEntity.getName(), cardTierEntity.getIcon(), userModelDomain);
    }

    public static CardTierEntity toCardTierEntity(CardTierModel cardTierModel) {
        UserEntity userEntity = UserMapper.toUserEntity(cardTierModel.getCreatedBy());
        return new CardTierEntity(cardTierModel.getId(), cardTierModel.getName(), cardTierModel.getIcon(), userEntity);
    }
}
