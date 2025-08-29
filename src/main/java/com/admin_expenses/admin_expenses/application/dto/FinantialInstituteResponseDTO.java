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
public class FinantialInstituteResponseDTO extends ResponseGeneric {
    private String name;
    private String type;

    public FinantialInstituteResponseDTO() {
        super();
    }
}
