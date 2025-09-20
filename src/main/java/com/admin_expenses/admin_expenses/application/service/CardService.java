package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.model.CardType;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstituteModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import com.admin_expenses.admin_expenses.infrastructure.mapper.CardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService implements ICardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final FinantialInstituteRepository finantialInstituteRepository;

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

        CardType cardType = this.getCardType(dto.getCardType());
        CardModel cardModel = new CardModel();
        cardModel.setAlias(dto.getAlias());
        cardModel.setCardType(cardType);
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

        if (finantialInstituteModel == null) throw new FinantialInstituteNotFoundException(dto.getFinantialInstituteId());
        if (cardModelFindedOpt == null) throw new CardNotFoundException(dto.getCardId());

        CardType cardType = this.getCardType(dto.getCardType());

        cardModelFindedOpt.setFinantialInstituteModel(finantialInstituteModel);
        cardModelFindedOpt.setCardType(cardType);
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

        return CardMapper.mapperToCardResponseDTO(cardModel);
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

        return cardModelList.stream().map(CardMapper::mapperToCardResponseDTO).toList();
    }

    private CardType getCardType(String cardType) {
        try {
            return CardType.valueOf(cardType);
        } catch (IllegalArgumentException e) {
            throw new BusinessException("Tipo de tarjeta no valido: " + cardType);
        }
    }
}
