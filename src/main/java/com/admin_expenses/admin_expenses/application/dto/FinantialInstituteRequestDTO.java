package com.admin_expenses.admin_expenses.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinantialInstituteRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "El nombre es obligatorio")
    String name;

    @NotBlank(message = "El tipo es obligatorio")
    String type;

    @NotBlank(message = "El username del usuario es obligatorio")
    String usernameCreatedBy;
}
