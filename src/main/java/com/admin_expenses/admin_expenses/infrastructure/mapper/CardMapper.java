package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class CardMapper {
    public static CardModel toDomainCard(CardEntity cardEntity) {
        UserModel userModelDomain = UserMapper.toDomainUser(cardEntity.getCreatedBy());
        FinantialInstituteModel finantialInstituteModelDomain = FinantialInstituteMapper.toDomainFinantianInstitute(cardEntity.getFinantialInstituteEntity());
        return new CardModel(cardEntity.getId(), cardEntity.getCardType(), finantialInstituteModelDomain, cardEntity.getAlias(), userModelDomain);
    }

    public static CardEntity toCardEntity(CardModel cardModel) {
        UserEntity userEntity = UserMapper.toUserEntity(cardModel.getCreatedBy());
        FinantialInstituteEntity finantialInstituteEntity = FinantialInstituteMapper.toFinantialInstitute(cardModel.getFinantialInstituteModel());
        return new CardEntity(cardModel.getId(), cardModel.getCardType(), finantialInstituteEntity, cardModel.getAlias(), userEntity);
    }
}
