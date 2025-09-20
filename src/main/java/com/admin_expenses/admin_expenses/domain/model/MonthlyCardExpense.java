package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.YearMonth;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyCardExpense {
    private CardModel cardModel;
    private YearMonth yearMonth;
    private Double total;
}
