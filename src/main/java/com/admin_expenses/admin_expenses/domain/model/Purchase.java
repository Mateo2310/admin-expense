package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Purchase {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double costTotal;
    private String purchaseType;
    private Integer fees;
    private Card card;
    private Date createdAt;
    private Date updatedAt;
    private User createdBy;

    public Purchase(String productName, Integer quantity, Double costTotal, String purchaseType, Integer fees, Card card, Date createdAt, Date updatedAt, User createdBy) {
        this.productName = productName;
        this.quantity = quantity;
        this.costTotal = costTotal;
        this.purchaseType = purchaseType;
        this.fees = fees;
        this.card = card;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
    }

    public Purchase() {
    }
}
