package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardTierRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardTierResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardTierService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.CardTierModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.domain.repository.CardTierRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class CardTierService implements ICardTierService {
    private final CardTierRepository cardTierRepository;

    @Override
    public String create(CardTierRequestDTO dto, UserModel userModel) {
        try {
            CardTierModel cardTierModel = new CardTierModel();
            cardTierModel.setName(dto.getName());
            cardTierModel.setIcon("/icon");
            cardTierModel.setCreatedAt(new Date());
            cardTierModel.setUpdatedAt(new Date());
            cardTierModel.setCreatedBy(userModel);
            this.cardTierRepository.save(cardTierModel);
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
    public String deleteById(Long id, Long userId) {
        this.cardTierRepository.deleteById(id, userId);
        return "SUCCESS";
    }

    @Override
    public CardTierResponseDTO findById(Long id, Long userId) {
        try {
            CardTierModel cardTierModel = this.cardTierRepository.findById(id, userId);
            if (cardTierModel == null) {
                throw new CardTierNotFoundException(id);
            }
            CardTierResponseDTO cardTierResponseDTO = new CardTierResponseDTO();
            cardTierResponseDTO.setName(cardTierModel.getName());
            cardTierResponseDTO.setIcon(cardTierModel.getIcon());
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
    public List<CardTierResponseDTO> findAll(Long userId) {
        List<CardTierModel> cardTierModels = this.cardTierRepository.findAll(userId);
        if (cardTierModels.isEmpty()) {
            return new ArrayList<>();
        }
        return cardTierModels.stream().map(cardTierModel -> {
            CardTierResponseDTO cardTierResponseDTO = new CardTierResponseDTO();
            cardTierResponseDTO.setName(cardTierModel.getName());
            cardTierResponseDTO.setIcon(cardTierModel.getIcon());
            cardTierResponseDTO.setId(cardTierModel.getId());
            return cardTierResponseDTO;
        }).toList();
    }
}
