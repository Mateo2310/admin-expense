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
@AllArgsConstructor
public class PurchaseResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String status;
    private String message;

    public PurchaseResponseDTO() {
    }
}
