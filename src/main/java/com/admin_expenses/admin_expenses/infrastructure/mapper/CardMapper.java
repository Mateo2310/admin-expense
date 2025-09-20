package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.model.CardTierModel;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardTierEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.FinantialInstituteEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class CardMapper {
    public static CardModel toDomainCard(CardEntity cardEntity) {
        UserModel userModelDomain = UserMapper.toDomainUser(cardEntity.getCreatedBy());
        FinantialInstituteModel finantialInstituteModelDomain = FinantialInstituteMapper.toDomainFinantianInstitute(cardEntity.getFinantialInstituteEntity());
        CardTierModel cardTierModel = CardTierMapper.toDomainCardTier(cardEntity.getCardTier());
        return new CardModel(cardEntity.getId(), cardEntity.getCardType(), finantialInstituteModelDomain, cardTierModel, cardEntity.getAlias(), userModelDomain);
    }

    public static CardEntity toCardEntity(CardModel cardModel) {
        UserEntity userEntity = UserMapper.toUserEntity(cardModel.getCreatedBy());
        FinantialInstituteEntity finantialInstituteEntity = FinantialInstituteMapper.toFinantialInstitute(cardModel.getFinantialInstituteModel());
        CardTierEntity cardTierEntity = CardTierMapper.toCardTierEntity(cardModel.getCardTierModel());
        return new CardEntity(cardModel.getId(), cardModel.getCardType(), finantialInstituteEntity, cardTierEntity, cardModel.getAlias(), userEntity);
    }

    public static CardResponseDTO mapperToCardResponseDTO(CardModel cardModel) {
        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        cardResponseDTO.setId(cardModel.getId());
        cardResponseDTO.setCardType(cardModel.getCardType().name());
        cardResponseDTO.setAlias(cardModel.getAlias());
        cardResponseDTO.setFinantialInstituteName(cardModel.getFinantialInstituteModel().getName());
        cardResponseDTO.setFinantialInstituteType(cardModel.getFinantialInstituteModel().getType());
        return cardResponseDTO;
    }
}
