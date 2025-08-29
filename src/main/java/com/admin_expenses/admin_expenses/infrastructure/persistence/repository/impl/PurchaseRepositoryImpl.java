package com.admin_expenses.admin_expenses.infrastructure.persistence.repository.impl;

import com.admin_expenses.admin_expenses.domain.model.Purchase;
import com.admin_expenses.admin_expenses.domain.repository.PurchaseRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.entity.PurchaseEntity;
import com.admin_expenses.admin_expenses.infrastructure.persistence.mapper.PurchaseMapper;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IPurchaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PurchaseRepositoryImpl implements PurchaseRepository {
    private final IPurchaseRepository iPurchaseRepository;

    public PurchaseRepositoryImpl(IPurchaseRepository iPurchaseRepository) {
        this.iPurchaseRepository = iPurchaseRepository;
    }

    @Override
    public Optional<Purchase> findById(Long id) {
        return this.iPurchaseRepository.findById(id)
                .map(PurchaseMapper::toDomainPurchase);
    }

    @Override
    public Purchase save(Purchase purchase) {
        PurchaseEntity purchaseEntity = PurchaseMapper.toDomainPurchase(purchase);
        PurchaseEntity savedEntity = this.iPurchaseRepository.save(purchaseEntity);
        return PurchaseMapper.toDomainPurchase(savedEntity);
    }

    @Override
    public Purchase update(Purchase purchase) {
        return PurchaseMapper.toDomainPurchase(this.iPurchaseRepository.save(PurchaseMapper.toDomainPurchase(purchase)));
    }

    @Override
    public void delete(Purchase purchase) {
        this.iPurchaseRepository.delete(PurchaseMapper.toDomainPurchase(purchase));
    }

    @Override
    public void deleteById(Long id) {
        this.iPurchaseRepository.deleteById(id);
    }

    @Override
    public List<Purchase> findAll() {
        return this.iPurchaseRepository.findAll()
                .stream().map(PurchaseMapper::toDomainPurchase).collect(Collectors.toList());
    }

    @Override
    public Optional<Purchase> findByUserId(Long userId) {
        return Optional.empty();
    }
}
