package com.admin_expenses.admin_expenses.domain.model;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CardModel {
    private Long id;
    private CardType cardType;
    private FinantialInstituteModel finantialInstituteModel;
    private CardTierModel cardTierModel;
    private String alias;
    private UserModel createdBy;

    public CardModel(CardType cardType, FinantialInstituteModel finantialInstituteModel, CardTierModel cardTierModel, String alias, UserModel createdBy) {
        this.cardType = cardType;
        this.finantialInstituteModel = finantialInstituteModel;
        this.cardTierModel = cardTierModel;
        this.alias = alias;
        this.createdBy = createdBy;
    }
}
