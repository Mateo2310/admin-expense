package com.admin_expenses.admin_expenses.infrastructure.persistence.mapper;

import com.admin_expenses.admin_expenses.domain.model.Card;
import com.admin_expenses.admin_expenses.domain.model.Purchase;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.CardEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.PurchaseEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.UserEntity;

public class PurchaseMapper {
    public static Purchase toDomainPurchase(PurchaseEntity purchaseEntity) {
        User user = UserMapper.toDomainUser(purchaseEntity.getCreatedBy());
        Card card = CardMapper.toDomainCard(purchaseEntity.getCard());
        return new Purchase(purchaseEntity.getId(), purchaseEntity.getProductName(), purchaseEntity.getQuantity(), purchaseEntity.getCostTotal(), purchaseEntity.getPurchaseType(), purchaseEntity.getFees(), card, purchaseEntity.getCreatedAt(), purchaseEntity.getUpdatedAt(), user);
    }

    public static PurchaseEntity toDomainPurchase(Purchase purchase) {
        CardEntity cardEntity = CardMapper.toCardEntity(purchase.getCard());
        UserEntity userEntity = UserMapper.toUserEntity(purchase.getCreatedBy());

        return new PurchaseEntity(purchase.getId(), purchase.getProductName(), purchase.getQuantity(), purchase.getCostTotal(), purchase.getPurchaseType(), purchase.getFees(), cardEntity, purchase.getCreatedAt(), purchase.getUpdatedAt(), userEntity);
    }
}
