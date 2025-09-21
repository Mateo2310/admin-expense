package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces;

import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
    void deleteByIdAndUserId(Long id, Long userId);
    Optional<PurchaseEntity> findByIdAndUserId(Long id, Long userId);
    List<PurchaseEntity> findAllByUserId(Long userId);
}
