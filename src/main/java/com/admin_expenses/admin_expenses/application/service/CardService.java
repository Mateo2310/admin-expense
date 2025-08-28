package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.ICardService;
import com.admin_expenses.admin_expenses.domain.model.Card;
import com.admin_expenses.admin_expenses.domain.model.FinantialInstitute;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.domain.repository.FinantialInstituteRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import com.admin_expenses.admin_expenses.infrastructure.persistence.mapper.FinantialInstituteMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Optional<FinantialInstitute> finantialInstitute = this.finantialInstituteRepository.findById(dto.getFinantialInstituteId());
        Optional<User> userEntity = this.userRepository.findById(dto.getUserId());
        if (finantialInstitute.isEmpty() || userEntity.isEmpty()) {
            return "ERROR";
        }

        Card card = new Card();
        card.setAlias(dto.getAlias());
        card.setCardType(dto.getCardType());
        card.setFinantialInstitute(finantialInstitute.get());
        card.setCreatedAt(new Date());
        card.setUpdatedAt(new Date());
        card.setCreatedBy(userEntity.get());

        Card cardSaved = this.cardRepository.save(card);
        if (cardSaved == null) {
            return "ERROR";
        }
        return "SUCCESS";
    }

    @Override
    public String delete(String name) {return "";}

    @Override
    public String update(CardRequestDTO dto) {
        Optional<Card> cardFindedOpt = this.cardRepository.findById(dto.getCardId());
        Optional<FinantialInstitute> finantialInstitute = this.finantialInstituteRepository.findById(dto.getFinantialInstituteId());
        if  (cardFindedOpt.isEmpty() || finantialInstitute.isEmpty()) {
            return "ERROR";
        }

        Card cardFinded = cardFindedOpt.get();
        cardFinded.setFinantialInstitute(finantialInstitute.get());
        cardFinded.setCardType(dto.getCardType());
        cardFinded.setAlias(dto.getAlias());
        cardFinded.setUpdatedAt(new Date());
        return "SUCCESS";
    }

    @Override
    public String deleteById(Long id) {
        Optional<Card> cardFindedOpt = this.cardRepository.findById(id);
        if (cardFindedOpt.isEmpty()) {
            return "ERROR";
        }
        this.deleteById(cardFindedOpt.get().getId());
        return "SUCCESS";
    }

    @Override
    public CardResponseDTO findById(Long id) {
        Optional<Card> cardFindedOpt = this.cardRepository.findById(id);
        if (cardFindedOpt.isEmpty()) {
            return null;
        }

        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        cardResponseDTO.setMessage(cardFindedOpt.get().getCardType());
        return cardResponseDTO;
    }

    @Override
    public List<CardResponseDTO> findAll() {
        return List.of();
    }
}
