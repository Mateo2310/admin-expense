package com.admin_expenses.admin_expenses.domain.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardModel {
    private Long id;
    private String cardType;
    private FinantialInstituteModel finantialInstituteModel;
    private String alias;
    private UserModel createdBy;

    public CardModel(String cardType, FinantialInstituteModel finantialInstituteModel, String alias, UserModel createdBy) {
        this.cardType = cardType;
        this.finantialInstituteModel = finantialInstituteModel;
        this.alias = alias;
        this.createdBy = createdBy;
    }
}
