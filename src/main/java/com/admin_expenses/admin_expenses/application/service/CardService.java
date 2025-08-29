package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.Card;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.model.User;
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
        FinantialInstitute finantialInstitute = this.finantialInstituteRepository.findById(dto.getFinantialInstituteId());
        User userEntity = this.userRepository.findById(dto.getUserId());
        if (finantialInstitute == null) {
            throw new FinantialInstituteNotFoundException(dto.getFinantialInstituteId());
        }
        if (userEntity == null) {
            throw new UserNotFoundException(dto.getUserId());
        }
        Card card = new Card();
        card.setAlias(dto.getAlias());
        card.setCardType(dto.getCardType());
        card.setFinantialInstitute(finantialInstitute);
        card.setCreatedAt(new Date());
        card.setUpdatedAt(new Date());
        card.setCreatedBy(userEntity);

        try {
            this.cardRepository.save(card);
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
        Card cardFindedOpt = this.cardRepository.findById(dto.getCardId());
        FinantialInstitute finantialInstitute = this.finantialInstituteRepository.findById(dto.getFinantialInstituteId());
        if (finantialInstitute == null) {
            throw new FinantialInstituteNotFoundException(dto.getFinantialInstituteId());
        }
        if (cardFindedOpt == null) {
            throw new CardNotFoundException(dto.getCardId());
        }

        cardFindedOpt.setFinantialInstitute(finantialInstitute);
        cardFindedOpt.setCardType(dto.getCardType());
        cardFindedOpt.setAlias(dto.getAlias());
        cardFindedOpt.setUpdatedAt(new Date());

        try {
            this.cardRepository.update(cardFindedOpt);
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
        Card cardFindedOpt = this.cardRepository.findById(id);
        if (cardFindedOpt == null) {
            throw new CardNotFoundException(id);
        }

        try {
            this.cardRepository.deleteById(cardFindedOpt.getId());
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
        Card card;
        try {
            card = this.cardRepository.findById(id);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }

        if (card == null) {
            throw new CardNotFoundException(id);
        }

        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        cardResponseDTO.setCardType(card.getCardType());
        cardResponseDTO.setAlias(card.getAlias());
        cardResponseDTO.setFinantialInstituteName(card.getFinantialInstitute().getName());
        cardResponseDTO.setFinantialInstituteType(card.getFinantialInstitute().getType());
        cardResponseDTO.setMessage("SUCCESS");
        cardResponseDTO.setStatus("200");
        return cardResponseDTO;
    }

    @Override
    public List<CardResponseDTO> findAll() {
        List<Card> cardList;
        try {
            cardList = this.cardRepository.findAll();
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }

        if  (cardList == null) {
            return new ArrayList<>();
        }

        return cardList.stream().map(card -> {
            CardResponseDTO cardResponseDTO = new CardResponseDTO();
            cardResponseDTO.setMessage("SUCCESS");
            cardResponseDTO.setCardType(card.getCardType());
            cardResponseDTO.setAlias(card.getAlias());
            cardResponseDTO.setStatus("200");
            cardResponseDTO.setFinantialInstituteName(card.getFinantialInstitute().getName());
            cardResponseDTO.setFinantialInstituteType(card.getFinantialInstitute().getType());
            return cardResponseDTO;
        }).toList();
    }
}
