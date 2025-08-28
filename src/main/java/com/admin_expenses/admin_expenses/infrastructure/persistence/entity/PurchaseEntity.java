package com.admin_expenses.admin_expenses.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "purchases")
@Getter
@Setter
@AllArgsConstructor
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "cost_total")
    private Double costTotal;

    @Column(name = "purchase_type")
    private String purchaseType;

    @Column(name = "fees")
    private Integer fees;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", nullable = false)
    private CardEntity card;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity createdBy;

    public PurchaseEntity(String productName, Integer quantity, Double costTotal, String purchaseType, Integer fees, CardEntity card, Date createdAt, Date updatedAt, UserEntity createdBy) {
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

    public PurchaseEntity() {}
}
