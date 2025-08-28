package com.admin_expenses.admin_expenses.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class PurchaseRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String productName;
    private Integer quantity;
    private Double costTotal;
    private String purchaseType;
    private Integer fees;
    private Long cardId;
    private Long userId;

    public PurchaseRequestDTO() {
    }
}
