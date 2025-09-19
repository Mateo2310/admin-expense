package com.admin_expenses.admin_expenses.domain.model;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardTierModel {
    private Long id;
    private String name;
    private String icon;
    private Date createdAt;
    private Date updatedAt;
    private UserModel createdBy;

    public CardTierModel(Long id, String name, String icon, UserModel createdBy) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.createdBy = createdBy;
    }
}
