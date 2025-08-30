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
public class CardResponseDTO extends ResponseGeneric {
    private String cardType;
    private String alias;
    private String finantialInstituteName;
    private String finantialInstituteType;

    public CardResponseDTO() {
        super();
    }
}
