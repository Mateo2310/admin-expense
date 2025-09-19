package com.admin_expenses.admin_expenses.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "card_tiers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardTierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false) // Clave foránea en "usuarios"
    private UserEntity createdBy;

    public CardTierEntity(Long id, String name, String icon, UserEntity createdBy) {
        this.id = id;
        this.name = name;
        this.icon = icon;
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
