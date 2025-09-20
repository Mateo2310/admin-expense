package com.admin_expenses.admin_expenses.domain.logic;

import com.admin_expenses.admin_expenses.domain.model.MonthlyCardExpense;
import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExpenseCalculator {
    public Double totalExpenses(List<PurchaseModel> purchaseModels) {
        return purchaseModels.stream()
                .map(purchaseModel -> purchaseModel.getInstallmentAmount() * purchaseModel.getQuantity())
                .reduce(0.0, Double::sum);
    }

    public Map<String, Double> totalCardExpenses(List<PurchaseModel> purchaseModels) {
        return purchaseModels.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCardModel().getAlias(),
                        Collectors.summingDouble(PurchaseModel::getInstallmentAmount)
                ));
    }

    public Map<String, Double> totalExpensesPerMonth(List<PurchaseModel> purchaseModels) {
        return purchaseModels.stream()
                .collect(Collectors.groupingBy(
                        purchaseModel -> purchaseModel.getPurchaseDate().toString(),
                        Collectors.summingDouble(PurchaseModel::getInstallmentAmount)
                ));
    }

    public List<MonthlyCardExpense> totalExpensesByMonthAndCard(List<PurchaseModel> purchaseModels) {

    }
}
