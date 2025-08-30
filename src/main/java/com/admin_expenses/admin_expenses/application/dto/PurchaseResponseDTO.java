package com.admin_expenses.admin_expenses.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class PurchaseResponseDTO extends ResponseGeneric {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double costTotal;
    private String purchaseType;
    private Integer fees;
    private CardResponseDTO card;

    public PurchaseResponseDTO() {
        super();
    }
}
