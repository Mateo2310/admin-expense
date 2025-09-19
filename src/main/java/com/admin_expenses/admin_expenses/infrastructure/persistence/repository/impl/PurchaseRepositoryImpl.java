package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;
import com.admin_expenses.admin_expenses.domain.repository.PurchaseRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.PurchaseEntity;
import com.admin_expenses.admin_expenses.infrastructure.mapper.PurchaseMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IPurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {
    private final IPurchaseRepository iPurchaseRepository;

    public PurchaseRepositoryImpl(IPurchaseRepository iPurchaseRepository) {
        this.iPurchaseRepository = iPurchaseRepository;
    }

    @Override
    public PurchaseModel findById(Long id) {
        return this.iPurchaseRepository.findById(id)
                .map(PurchaseMapper::toModelPurchase).orElse(null);
    }

    @Override
    public PurchaseModel save(PurchaseModel purchaseModel) {
        PurchaseEntity purchaseEntity = PurchaseMapper.toEntityPurchase(purchaseModel);
        PurchaseEntity savedEntity = this.iPurchaseRepository.save(purchaseEntity);
        return PurchaseMapper.toModelPurchase(savedEntity);
    }

    @Override
    public PurchaseModel update(PurchaseModel purchaseModel) {
        return PurchaseMapper.toModelPurchase(this.iPurchaseRepository.save(PurchaseMapper.toEntityPurchase(purchaseModel)));
    }

    @Override
    public void delete(PurchaseModel purchaseModel) {
        this.iPurchaseRepository.delete(PurchaseMapper.toEntityPurchase(purchaseModel));
    }

    @Override
    public void deleteById(Long id) {
        this.iPurchaseRepository.deleteById(id);
    }

    @Override
    public List<PurchaseModel> findAll() {
        return this.iPurchaseRepository.findAll()
                .stream().map(PurchaseMapper::toModelPurchase).collect(Collectors.toList());
    }

    @Override
    public PurchaseModel findByUserId(Long userId) {
        return null;
    }
}
