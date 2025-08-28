package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Card {
    private Long id;
    private String cardType;
    private FinantialInstitute finantialInstitute;
    private String alias;
    private Date createdAt;
    private Date updatedAt;
    private User createdBy;

    public Card(String cardType, FinantialInstitute finantialInstitute, String alias, User createdBy, Date createdAt, Date updatedAt) {
        this.cardType = cardType;
        this.finantialInstitute = finantialInstitute;
        this.alias = alias;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Card() {}
}
