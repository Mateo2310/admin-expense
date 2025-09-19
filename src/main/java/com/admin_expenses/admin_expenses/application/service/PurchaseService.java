package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IPurchaseService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.domain.repository.CardRepository;
import com.admin_expenses.admin_expenses.domain.repository.PurchaseRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import com.admin_expenses.admin_expenses.infrastructure.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService implements IPurchaseService {
    private final UserRepository userRepository;
    private final PurchaseRepository purchaseRepository;
    private final CardRepository cardRepository;

    @Override
    public String create(PurchaseRequestDTO dto) {
        try {
            UserModel userModelFinded = this.userRepository.findById(dto.getUserId());
            CardModel cardModelFinded = this.cardRepository.findById(dto.getCardId());
            if (userModelFinded == null) {
                throw new UserNotFoundException(dto.getUserId());
            }
            if (cardModelFinded == null) {
                throw new CardNotFoundException(dto.getCardId());
            }
            PurchaseModel purchaseModel = PurchaseMapper.fromRequestDtoToModelPurchase(dto, userModelFinded, cardModelFinded);
            purchaseModel.validate();
            this.purchaseRepository.save(purchaseModel);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el gasto", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el gasto", e);
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
            PurchaseModel purchaseModel = this.purchaseRepository.findById(dto.getId());
            purchaseModel.setProductName(dto.getProductName());
            purchaseModel.setQuantity(dto.getQuantity());
            purchaseModel.setInstallmentAmount(dto.getInstallmentAmount());
            purchaseModel.setPurchaseType(dto.getPurchaseType());
            purchaseModel.setFees(dto.getFees());
            this.purchaseRepository.update(purchaseModel);
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el gasto", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el gasto", e);
        }
    }

    @Override
    public String deleteById(Long id) {
        try {
            this.purchaseRepository.deleteById(id);
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el gasto", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el gasto", e);
        }
    }

    @Override
    public PurchaseResponseDTO findById(Long id) {
        try {
            PurchaseModel purchaseModel = this.purchaseRepository.findById(id);
            return PurchaseMapper.getPurchaseResponseDTO(purchaseModel);
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el gasto", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el gasto", e);
        }
    }

    @Override
    public List<PurchaseResponseDTO> findAll() {
        try {
            List<PurchaseModel> purchaseModelList = this.purchaseRepository.findAll();
            return purchaseModelList.stream().map(PurchaseMapper::getPurchaseResponseDTO).toList();
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el gasto", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el gasto", e);
        }
    }
}
