package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardService;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService implements ICardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String create(CardRequestDTO dto) {
        return "";
    }

    @Override
    public String delete(String name) {
        return "";
    }

    @Override
    public String update(CardRequestDTO dto) {
        return "";
    }

    @Override
    public String deleteById(Long id) {
        return "";
    }

    @Override
    public CardResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<CardResponseDTO> findAll() {
        return List.of();
    }
}
