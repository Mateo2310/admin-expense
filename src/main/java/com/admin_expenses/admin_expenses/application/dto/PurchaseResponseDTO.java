package com.admin_expenses.admin_expenses.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDTO {
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal installmentAmount;
    private String purchaseType;
    private Integer fees;
    private CardResponseDTO card;
}
