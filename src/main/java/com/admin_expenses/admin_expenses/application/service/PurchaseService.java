package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IPurchaseService;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IPurchaseRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService implements IPurchaseService {
    private final IUserRepository userRepository;
    private final IPurchaseRepository purchaseRepository;

    public PurchaseService(IUserRepository userRepository, IPurchaseRepository purchaseRepository) {
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public String create(PurchaseRequestDTO dto) {
        return "";
    }

    @Override
    public String delete(String name) {
        return "";
    }

    @Override
    public String update(PurchaseRequestDTO dto) {
        return "";
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }

    @Override
    public PurchaseResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<PurchaseResponseDTO> findAll() {
        return List.of();
    }
}
