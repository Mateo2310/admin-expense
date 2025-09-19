package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardTierRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardTierResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardTierService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.CardTierModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
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
            UserModel userModelFinded = this.userRepository.findById(dto.getUserId());
            if (userModelFinded == null) {
                throw new UserNotFoundException(dto.getUserId());
            }
            CardTierModel cardTierModel = new CardTierModel();
            cardTierModel.setName(dto.getName());
            cardTierModel.setIcon("/icon");
            cardTierModel.setCreatedAt(new Date());
            cardTierModel.setUpdatedAt(new Date());
            cardTierModel.setCreatedBy(userModelFinded);
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
    public String delete(String name) {
        try {
            CardTierModel cardTierModel = this.cardTierRepository.findByName(name);
            if (cardTierModel == null) {
                throw new CardTierNotFoundException(name);
            }
            this.deleteById(cardTierModel.getId());
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
            CardTierModel cardTierModel = this.cardTierRepository.findByName(dto.getName());
            cardTierModel.setName(dto.getName());
            cardTierModel.setIcon("/icon");
            cardTierModel.setUpdatedAt(new Date());

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
    public String deleteById(Long id) {
        this.cardTierRepository.deleteById(id);
        return "SUCCESS";
    }

    @Override
    public CardTierResponseDTO findById(Long id) {
        try {
            CardTierModel cardTierModel = this.cardTierRepository.findById(id);
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
    public List<CardTierResponseDTO> findAll() {
        List<CardTierModel> cardTierModels = this.cardTierRepository.findAll();
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
