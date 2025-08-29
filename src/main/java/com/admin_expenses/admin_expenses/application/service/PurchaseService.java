package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IPurchaseService;
import com.admin_expenses.admin_expenses.domain.model.Card;
import com.admin_expenses.admin_expenses.domain.model.Purchase;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.domain.repository.PurchaseRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService implements IPurchaseService {
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final CardRepository cardRepository;

    public PurchaseService(UserRepository userRepository, PurchaseRepository purchaseRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.purchaseRepository = purchaseRepository;
        this.cardRepository = cardRepository;
    }

    @Override
    public String create(PurchaseRequestDTO dto) {
        Optional<User> userFinded = userRepository.findById(dto.getUserId());
        Optional<Card> cardFinded = cardRepository.findById(dto.getCardId());
        if(userFinded.isEmpty() || cardFinded.isEmpty()) {
            return "User not found";
        }
        Card cardSaved = cardFinded.get();
        Purchase purchase = new Purchase();
        purchase.setCard(cardSaved);
        purchase.setProductName(dto.getProductName());
        purchase.setQuantity(dto.getQuantity());
        purchase.setCostTotal(dto.getCostTotal());
        purchase.setPurchaseType(dto.getPurchaseType());
        purchase.setFees(dto.getFees());
        purchase.setCreatedAt(new Date());
        purchase.setUpdatedAt(new Date());
        purchase.setCreatedBy(userFinded.get());
        this.purchaseRepository.save(purchase);
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
