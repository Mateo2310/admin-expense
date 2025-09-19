package com.admin_expenses.admin_expenses.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El id es obligatorio")
    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String productName;

    @NotNull(message = "La cantidad es obligatoria")
    private Integer quantity;

    @NotNull(message = "El monto de la cuota es obligatoria")
    private Double installmentAmount;

    @NotBlank(message = "El tipo de gasto es obligatorio")
    private String purchaseType;

    @NotNull(message = "La tarifa es obligatoria")
    private Integer fees;

    @NotNull(message = "El id de la tarjeta es obligatoria")
    private Long cardId;

    @NotNull(message = "El id del usuario es obligatoria")
    private Long userId;
}
