package com.admin_expenses.admin_expenses.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CardRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El ID es obligatorio")
    private Long cardId;

    @NotBlank(message = "El tipo de tarjeta es obligatorio")
    private String cardType;

    @NotBlank(message = "El alias es obligatorio")
    private String alias;

    @NotNull(message = "El user id es obligatorio")
    private Long userId;

    @NotNull(message = "El id de la entidad financiera es obligatorio")
    private Long finantialInstituteId;
}
