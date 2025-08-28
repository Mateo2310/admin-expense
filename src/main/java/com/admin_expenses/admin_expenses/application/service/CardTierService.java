package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardTierRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardTierResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardTierService;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.ICardTierRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.repository.interfaces.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardTierService implements ICardTierService {
    private final IUserRepository userRepository;
    private final ICardTierRepository cardTierRepository;

    public CardTierService(IUserRepository userRepository, ICardTierRepository cardTierRepository) {
        this.userRepository = userRepository;
        this.cardTierRepository = cardTierRepository;
    }

    @Override
    public String create(CardTierRequestDTO dto) {
        return "";
    }

    @Override
    public String delete(String name) {
        return "";
    }

    @Override
    public String update(CardTierRequestDTO dto) {
        return "";
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }

    @Override
    public CardTierResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<CardTierResponseDTO> findAll() {
        return List.of();
    }
}
