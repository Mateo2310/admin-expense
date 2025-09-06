package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardTierRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardTierResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardTierService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.CardTier;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.CardTierRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardTierService implements ICardTierService {
    private final UserRepository userRepository;
    private final CardTierRepository cardTierRepository;

    public CardTierService(UserRepository userRepository, CardTierRepository cardTierRepository) {
        this.userRepository = userRepository;
        this.cardTierRepository = cardTierRepository;
    }

    @Override
    public String create(CardTierRequestDTO dto) {
        try {
            User userFinded = this.userRepository.findById(dto.getUserId());
            if (userFinded == null) {
                throw new UserNotFoundException(dto.getUserId());
            }
            CardTier cardTier = new CardTier();
            cardTier.setName(dto.getName());
            cardTier.setIcon("/icon");
            cardTier.setCreatedAt(new Date());
            cardTier.setUpdatedAt(new Date());
            cardTier.setCreatedBy(userFinded);
            this.cardTierRepository.save(cardTier);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el nivel de tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el nivel de tarjeta", e);
        }

        return "SUCCESS";
    }

    @Override
    public String delete(String name) {
        try {
            CardTier cardTier = this.cardTierRepository.findByName(name);
            if (cardTier == null) {
                throw new CardTierNotFoundException(name);
            }
            this.deleteById(cardTier.getId());
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el nivel de tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el nivel de tarjeta", e);
        }

        return "SUCCESS";
    }

    @Override
    public String update(CardTierRequestDTO dto) {
        try {
            CardTier cardTier = this.cardTierRepository.findByName(dto.getName());
            cardTier.setName(dto.getName());
            cardTier.setIcon("/icon");
            cardTier.setUpdatedAt(new Date());

            this.cardTierRepository.save(cardTier);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el nivel de tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el nivel de tarjeta", e);
        }

        return "SUCCESS";
    }

    @Override
    public String deleteById(Long id) {
        this.cardTierRepository.deleteById(id);
        return "SUCCESS";
    }

    @Override
    public CardTierResponseDTO findById(Long id) {
        try {
            CardTier cardTier = this.cardTierRepository.findById(id);
            if (cardTier == null) {
                throw new CardTierNotFoundException(id);
            }
            CardTierResponseDTO cardTierResponseDTO = new CardTierResponseDTO();
            cardTierResponseDTO.setName(cardTier.getName());
            cardTierResponseDTO.setIcon(cardTier.getIcon());
            return cardTierResponseDTO;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el nivel de tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el nivel de tarjeta", e);
        }
    }

    @Override
    public List<CardTierResponseDTO> findAll() {
        List<CardTier> cardTiers = this.cardTierRepository.findAll();
        if (cardTiers.isEmpty()) {
            return new ArrayList<>();
        }
        return cardTiers.stream().map(cardTier -> {
            CardTierResponseDTO cardTierResponseDTO = new CardTierResponseDTO();
            cardTierResponseDTO.setName(cardTier.getName());
            cardTierResponseDTO.setIcon(cardTier.getIcon());
            cardTierResponseDTO.setId(cardTier.getId());
            return cardTierResponseDTO;
        }).toList();
    }
}
