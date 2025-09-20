package com.admin_expenses.admin_expenses.domain.model;

import com.admin_expenses.admin_expenses.domain.exception.BusinessException;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    private Long id;
    private String productName;
    private Integer quantity;
    private BigDecimal installmentAmount;
    private String purchaseType;
    private Integer fees;
    private CardModel cardModel;
    private UserModel createdBy;
    private LocalDate purchaseDate;

    public void validate() {
        if (quantity <= 0) {
            throw new BusinessException("La cantidad debe ser mayor a cero");
        }

        if (fees <= 0) {
            throw new BusinessException("Las cuotas deben ser mayor a cero");
        }

        if (installmentAmount.signum() < 0) {
            throw new BusinessException("El precio no puede ser negativo");
        }
    }
}
