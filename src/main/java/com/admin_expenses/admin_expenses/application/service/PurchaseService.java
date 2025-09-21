package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IPurchaseService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.logic.ExpenseCalculator;
import com.admin_expenses.admin_expenses.domain.model.CardModel;
import com.admin_expenses.admin_expenses.domain.model.MonthlyCardExpense;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PurchaseService implements IPurchaseService {
    private final PurchaseRepository purchaseRepository;
    private final CardRepository cardRepository;
    private final ExpenseCalculator expenseCalculator;

    @Override
    public String create(PurchaseRequestDTO dto, UserModel userModel) {
        try {
            CardModel cardModelFinded = this.cardRepository.findById(dto.getCardId(), userModel.getId());
            if (cardModelFinded == null) {
                throw new CardNotFoundException(dto.getCardId());
            }
            PurchaseModel purchaseModel = PurchaseMapper.fromRequestDtoToModelPurchase(dto, userModel, cardModelFinded);
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
    public String deleteById(Long id, Long userId) {
        try {
            this.purchaseRepository.deleteById(id, userId);
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
    public PurchaseResponseDTO findById(Long id, Long userId) {
        try {
            PurchaseModel purchaseModel = this.purchaseRepository.findById(id, userId);
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
    public List<PurchaseResponseDTO> findAll(Long userId) {
        try {
            List<PurchaseModel> purchaseModelList = this.purchaseRepository.findAll(userId);
            return purchaseModelList.stream().map(PurchaseMapper::getPurchaseResponseDTO).toList();
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el gasto", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el gasto", e);
        }
    }

    @Override
    public BigDecimal getTotalExpenses(List<PurchaseModel> purchaseModels) {
        return this.expenseCalculator.totalExpenses(purchaseModels);
    }

    @Override
    public List<MonthlyCardExpense> getTotalExpensesPerMonthAndCard(List<PurchaseModel> purchaseModels) {
        return this.expenseCalculator.totalExpensesByMonthAndCard(purchaseModels);
    }

    @Override
    public Map<String, BigDecimal> getTotalExpensesPerMonth(List<PurchaseModel> purchaseModels) {
        return this.expenseCalculator.totalExpensesPerMonth(purchaseModels);
    }

    @Override
    public Map<String, BigDecimal> geTotalCardExpenses(List<PurchaseModel> purchaseModels) {
        return this.expenseCalculator.totalCardExpenses(purchaseModels);
    }
}
