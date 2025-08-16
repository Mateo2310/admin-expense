package com.admin_expenses.admin_expenses.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "cards")
@Getter
@Setter
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_type")
    private String cardType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "finantial_institute_id", nullable = false)
    private FinantialInstituteEntity finantialInstituteEntity;

    @Column(name = "alias")
    private String alias;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity createdBy;

    public CardEntity(String cardType, FinantialInstituteEntity finantialInstituteEntity, String alias, Date createdAt, Date updatedAt, UserEntity createdBy) {
        this.cardType = cardType;
        this.finantialInstituteEntity = finantialInstituteEntity;
        this.alias = alias;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdBy = createdBy;
    }

    public CardEntity() {

    }
}
