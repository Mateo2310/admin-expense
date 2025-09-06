package com.admin_expenses.admin_expenses.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDTO {
    private Long id;
    private String cardType;
    private String alias;
    private String finantialInstituteName;
    private String finantialInstituteType;
}
