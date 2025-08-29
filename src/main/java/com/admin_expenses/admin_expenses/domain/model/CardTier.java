package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CardTier {
    private Long id;
    private String name;
    private String icon;
    private Date createdAt;
    private Date updatedAt;
    private User createdBy;

    public CardTier(String name, String icon, Date createdAt, Date updatedAt, User createdBy) {
        this.name = name;
        this.icon = icon;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CardTier() {
    }
}
