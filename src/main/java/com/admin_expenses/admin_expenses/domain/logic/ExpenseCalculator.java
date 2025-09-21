package com.admin_expenses.admin_expenses.domain.logic;

import com.admin_expenses.admin_expenses.domain.model.MonthlyCardExpense;
import com.admin_expenses.admin_expenses.domain.model.PurchaseModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ExpenseCalculator {
    public BigDecimal totalExpenses(List<PurchaseModel> purchaseModels) {
        return purchaseModels.stream()
                .map(purchaseModel -> purchaseModel.getInstallmentAmount().multiply(BigDecimal.valueOf(purchaseModel.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> totalCardExpenses(List<PurchaseModel> purchaseModels) {
        return purchaseModels.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCardModel().getAlias(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                PurchaseModel::getInstallmentAmount,
                                BigDecimal::add
                        )
                ));
    }

    public Map<String, BigDecimal> totalExpensesPerMonth(List<PurchaseModel> purchaseModels) {
        DateTimeFormatter ym = DateTimeFormatter.ofPattern("yyyy-MM");

        return purchaseModels.stream()
                .collect(Collectors.groupingBy(
                        purchaseModel -> purchaseModel.getPurchaseDate().format(ym),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                PurchaseModel::getInstallmentAmount,
                                BigDecimal::add
                        )
                ));
    }

    public List<MonthlyCardExpense> totalExpensesByMonthAndCard(List<PurchaseModel> purchaseModels) {
        return purchaseModels.stream()
                .collect(Collectors.groupingBy(
                        purchaseModel -> new AbstractMap.SimpleEntry<>(
                                purchaseModel.getCardModel(),
                                YearMonth.from(purchaseModel.getPurchaseDate())
                        ),
                        Collectors.reducing(BigDecimal.ZERO,
                                PurchaseModel::getInstallmentAmount,
                                BigDecimal::add)
                ))
                .entrySet()
                .stream().map(
                        e -> new MonthlyCardExpense(
                                e.getKey().getKey(),
                                e.getKey().getValue(),
                                e.getValue()
                        )
                ).toList();
    }
}
