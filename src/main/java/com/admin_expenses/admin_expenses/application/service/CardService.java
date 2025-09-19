package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CardService implements ICardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final FinantialInstituteRepository finantialInstituteRepository;

    public CardService(CardRepository cardRepository, UserRepository userRepository, FinantialInstituteRepository finantialInstituteRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.finantialInstituteRepository = finantialInstituteRepository;
    }

    @Override
    public String create(CardRequestDTO dto) {
        FinantialInstituteModel finantialInstituteModel = this.finantialInstituteRepository.findById(dto.getFinantialInstituteId());
        UserModel userModelEntity = this.userRepository.findById(dto.getUserId());
        if (finantialInstituteModel == null) {
            throw new FinantialInstituteNotFoundException(dto.getFinantialInstituteId());
        }
        if (userModelEntity == null) {
            throw new UserNotFoundException(dto.getUserId());
        }
        CardModel cardModel = new CardModel();
        cardModel.setAlias(dto.getAlias());
        cardModel.setCardType(dto.getCardType());
        cardModel.setFinantialInstituteModel(finantialInstituteModel);
        cardModel.setCreatedBy(userModelEntity);

        try {
            this.cardRepository.save(cardModel);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }

        return "SUCCESS";
    }

    @Override
    public String delete(String name) {return "";}

    @Override
    public String update(CardRequestDTO dto) {
        CardModel cardModelFindedOpt = this.cardRepository.findById(dto.getCardId());
        FinantialInstituteModel finantialInstituteModel = this.finantialInstituteRepository.findById(dto.getFinantialInstituteId());
        if (finantialInstituteModel == null) {
            throw new FinantialInstituteNotFoundException(dto.getFinantialInstituteId());
        }
        if (cardModelFindedOpt == null) {
            throw new CardNotFoundException(dto.getCardId());
        }

        cardModelFindedOpt.setFinantialInstituteModel(finantialInstituteModel);
        cardModelFindedOpt.setCardType(dto.getCardType());
        cardModelFindedOpt.setAlias(dto.getAlias());

        try {
            this.cardRepository.update(cardModelFindedOpt);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }
        return "SUCCESS";
    }

    @Override
    public String deleteById(Long id) {
        CardModel cardModelFindedOpt = this.cardRepository.findById(id);
        if (cardModelFindedOpt == null) {
            throw new CardNotFoundException(id);
        }

        try {
            this.cardRepository.deleteById(cardModelFindedOpt.getId());
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }
        return "SUCCESS";
    }

    @Override
    public CardResponseDTO findById(Long id) {
        CardModel cardModel;
        try {
            cardModel = this.cardRepository.findById(id);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }

        if (cardModel == null) {
            throw new CardNotFoundException(id);
        }

        return mapperToCardResponseDTO(cardModel);
    }

    @Override
    public List<CardResponseDTO> findAll() {
        List<CardModel> cardModelList;
        try {
            cardModelList = this.cardRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }

        if  (cardModelList == null) {
            return new ArrayList<>();
        }

        return cardModelList.stream().map(this::mapperToCardResponseDTO).toList();
    }

    private CardResponseDTO mapperToCardResponseDTO(CardModel cardModel) {
        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        cardResponseDTO.setId(cardModel.getId());
        cardResponseDTO.setCardType(cardModel.getCardType());
        cardResponseDTO.setAlias(cardModel.getAlias());
        cardResponseDTO.setFinantialInstituteName(cardModel.getFinantialInstituteModel().getName());
        cardResponseDTO.setFinantialInstituteType(cardModel.getFinantialInstituteModel().getType());
        return cardResponseDTO;
    }
}
