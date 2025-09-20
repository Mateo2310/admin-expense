package com.admin_expenses.admin_expenses.infrastructure.persistence.entity;

import com.admin_expenses.admin_expenses.domain.model.CardType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "cards")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "card_type")
    private CardType cardType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "finantial_institute_id", nullable = false)
    private FinantialInstituteEntity finantialInstituteEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_tier_id", nullable = false)
    private CardTierEntity cardTier;

    @Column(name = "alias")
    private String alias;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity createdBy;

    public CardEntity(Long id, CardType cardType, FinantialInstituteEntity finantialInstituteEntity, CardTierEntity cardTierEntity, String alias, UserEntity createdBy) {
        this.id = id;
        this.cardType = cardType;
        this.finantialInstituteEntity = finantialInstituteEntity;
        this.cardTier = cardTierEntity;
        this.alias = alias;
        this.createdBy = createdBy;
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
