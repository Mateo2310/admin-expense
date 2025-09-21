package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.application.dto.PurchaseRequestDTO;
import com.admin_expenses.admin_expenses.application.dto.PurchaseResponseDTO;
import com.admin_expenses.admin_expenses.domain.model.MonthlyCardExpense;
import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IPurchaseService extends IServiceGeneric<PurchaseResponseDTO, PurchaseRequestDTO>{
    BigDecimal getTotalExpenses(List<PurchaseModel> purchaseModels);
    List<MonthlyCardExpense> getTotalExpensesPerMonthAndCard(List<PurchaseModel> purchaseModels);
    Map<String, BigDecimal> getTotalExpensesPerMonth(List<PurchaseModel> purchaseModels);
    Map<String, BigDecimal> geTotalCardExpenses(List<PurchaseModel> purchaseModels);
}
