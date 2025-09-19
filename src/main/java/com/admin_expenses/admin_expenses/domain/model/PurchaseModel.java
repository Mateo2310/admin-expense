package com.admin_expenses.admin_expenses.domain.model;

import com.admin_expenses.admin_expenses.domain.exception.BusinessException;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseModel {
    private Long id;
    private String productName;
    private Integer quantity;
    private Double installmentAmount;
    private String purchaseType;
    private Integer fees;
    private CardModel cardModel;
    private UserModel createdBy;

    public void validate() {
        if (quantity <= 0) {
            throw new BusinessException("La cantidad debe ser mayor a cero");
        }

        if (fees <= 0) {
            throw new BusinessException("Las cuotas deben ser mayor a cero");
        }

        if (installmentAmount < 0) {
            throw new BusinessException("El precio no puede ser negativo");
        }
    }
}
