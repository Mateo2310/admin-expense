package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;
import com.admin_expenses.admin_expenses.domain.repository.PurchaseRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.PurchaseEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.PurchaseMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IPurchaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class PurchaseRepositoryImpl implements PurchaseRepository {
    private final IPurchaseRepository iPurchaseRepository;

    @Override
    public PurchaseModel findById(Long id, Long userId) {
        return this.iPurchaseRepository.findByIdAndUserId(id, userId)
                .map(PurchaseMapper::toModelPurchase).orElse(null);
    }

    @Override
    public PurchaseModel save(PurchaseModel purchaseModel) {
        PurchaseEntity purchaseEntity = PurchaseMapper.toEntityPurchase(purchaseModel);
        PurchaseEntity savedEntity = this.iPurchaseRepository.save(purchaseEntity);
        return PurchaseMapper.toModelPurchase(savedEntity);
    }

    @Override
    public void deleteById(Long id, Long userId) {
        this.iPurchaseRepository.deleteByIdAndUserId(id, userId);
    }

    @Override
    public List<PurchaseModel> findAll(Long userId) {
        return this.iPurchaseRepository.findAllByUserId(userId)
                .stream().map(PurchaseMapper::toModelPurchase).collect(Collectors.toList());
    }
}
