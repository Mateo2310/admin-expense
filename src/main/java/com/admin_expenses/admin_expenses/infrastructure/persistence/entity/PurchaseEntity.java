package com.admin_expenses.admin_expenses.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "installment_amount")
    private Double installmentAmount;

    @Column(name = "purchase_type")
    private String purchaseType;

    @Column(name = "fees")
    private Integer fees;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id", nullable = false)
    private CardEntity card;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity createdBy;

    public PurchaseEntity(Long id, String productName, Integer quantity, Double installmentAmount, String purchaseType, Integer fees, CardEntity card, UserEntity createdBy, LocalDate purchaseDate) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.installmentAmount = installmentAmount;
        this.purchaseType = purchaseType;
        this.fees = fees;
        this.card = card;
        this.createdBy = createdBy;
        this.purchaseDate = purchaseDate;
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
