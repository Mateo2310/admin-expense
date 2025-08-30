package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.CardResponseDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IPurchaseService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.Card;
import com.admin_expenses.admin_expenses.domain.model.Purchase;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.domain.repository.PurchaseRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

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
        try {
            User userFinded = userRepository.findById(dto.getUserId());
            Card cardFinded = cardRepository.findById(dto.getCardId());
            if (userFinded == null) {
                throw new UserNotFoundException(dto.getUserId());
            }
            if (cardFinded == null) {
                throw new CardNotFoundException(dto.getCardId());
            }
            Purchase purchase = new Purchase();
            purchase.setCard(cardFinded);
            purchase.setProductName(dto.getProductName());
            purchase.setQuantity(dto.getQuantity());
            purchase.setCostTotal(dto.getCostTotal());
            purchase.setPurchaseType(dto.getPurchaseType());
            purchase.setFees(dto.getFees());
            purchase.setCreatedAt(new Date());
            purchase.setUpdatedAt(new Date());
            purchase.setCreatedBy(userFinded);
            this.purchaseRepository.save(purchase);
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
    public String delete(String name) {
        return "";
    }

    @Override
    public String update(PurchaseRequestDTO dto) {
        try {
            Purchase purchase = this.purchaseRepository.findById(dto.getId());
            purchase.setProductName(dto.getProductName());
            purchase.setQuantity(dto.getQuantity());
            purchase.setCostTotal(dto.getCostTotal());
            purchase.setPurchaseType(dto.getPurchaseType());
            purchase.setFees(dto.getFees());
            purchase.setUpdatedAt(new Date());
            this.purchaseRepository.update(purchase);
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }
    }

    @Override
    public String deleteById(Long id) {
        try {
            this.purchaseRepository.deleteById(id);
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }
    }

    @Override
    public PurchaseResponseDTO findById(Long id) {
        try {
            Purchase purchase = this.purchaseRepository.findById(id);
            return getPurchaseResponseDTO(purchase);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }
    }

    private static PurchaseResponseDTO getPurchaseResponseDTO(Purchase purchase) {
        PurchaseResponseDTO purchaseResponseDTO = new PurchaseResponseDTO();
        CardResponseDTO cardResponseDTO = new CardResponseDTO();
        purchaseResponseDTO.setMessage("Success");
        purchaseResponseDTO.setStatus("200");
        purchaseResponseDTO.setPurchaseType(purchase.getPurchaseType());
        purchaseResponseDTO.setFees(purchase.getFees());
        purchaseResponseDTO.setId(purchase.getId());
        purchaseResponseDTO.setProductName(purchase.getProductName());
        purchaseResponseDTO.setQuantity(purchase.getQuantity());
        purchaseResponseDTO.setCostTotal(purchase.getCostTotal());
        cardResponseDTO.setCardType(purchase.getCard().getCardType());
        cardResponseDTO.setCardType(purchase.getCard().getCardType());
        purchaseResponseDTO.setCard(cardResponseDTO);
        return purchaseResponseDTO;
    }

    @Override
    public List<PurchaseResponseDTO> findAll() {
        try {
            List<Purchase> purchaseList = this.purchaseRepository.findAll();
            return purchaseList.stream().map(PurchaseService::getPurchaseResponseDTO).toList();
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar la tarjeta", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar la tarjeta", e);
        }
    }
}
