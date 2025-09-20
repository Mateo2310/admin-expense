package com.admin_expenses.admin_expenses.infrastructure.mapper;

import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.PurchaseEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class PurchaseMapper {
    public static PurchaseModel toModelPurchase(PurchaseEntity purchaseEntity) {
        UserModel userModel = UserMapper.toDomainUser(purchaseEntity.getCreatedBy());
        CardModel cardModel = CardMapper.toDomainCard(purchaseEntity.getCard());
        return new PurchaseModel(purchaseEntity.getId(), purchaseEntity.getProductName(), purchaseEntity.getQuantity(), purchaseEntity.getInstallmentAmount(), purchaseEntity.getPurchaseType(), purchaseEntity.getFees(), cardModel, userModel, purchaseEntity.getPurchaseDate());
    }

    public static PurchaseEntity toEntityPurchase(PurchaseModel purchaseModel) {
        CardEntity cardEntity = CardMapper.toCardEntity(purchaseModel.getCardModel());
        UserEntity userEntity = UserMapper.toUserEntity(purchaseModel.getCreatedBy());

        return new PurchaseEntity(purchaseModel.getId(), purchaseModel.getProductName(), purchaseModel.getQuantity(), purchaseModel.getInstallmentAmount(), purchaseModel.getPurchaseType(), purchaseModel.getFees(), cardEntity, userEntity, purchaseModel.getPurchaseDate());
    }

    public static PurchaseModel fromRequestDtoToModelPurchase(PurchaseRequestDTO dto, UserModel userModel, CardModel cardModel) {
        PurchaseModel purchaseModel = new PurchaseModel();
        purchaseModel.setCardModel(cardModel);
        purchaseModel.setProductName(dto.getProductName());
        purchaseModel.setQuantity(dto.getQuantity());
        purchaseModel.setInstallmentAmount(dto.getInstallmentAmount());
        purchaseModel.setPurchaseType(dto.getPurchaseType());
        purchaseModel.setFees(dto.getFees());
        purchaseModel.setCreatedBy(userModel);
        return purchaseModel;
    }

    public static PurchaseResponseDTO getPurchaseResponseDTO(PurchaseModel purchaseModel) {
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        purchaseResponseDTO.setPurchaseType(purchaseModel.getPurchaseType());
        purchaseResponseDTO.setFees(purchaseModel.getFees());
        purchaseResponseDTO.setId(purchaseModel.getId());
        purchaseResponseDTO.setProductName(purchaseModel.getProductName());
        purchaseResponseDTO.setQuantity(purchaseModel.getQuantity());
        purchaseResponseDTO.setInstallmentAmount(purchaseModel.getInstallmentAmount());
        cardResponseDTO.setCardType(purchaseModel.getCardModel().getCardType().name());
        purchaseResponseDTO.setCard(cardResponseDTO);
        return purchaseResponseDTO;
    }
}
