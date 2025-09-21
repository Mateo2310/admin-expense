package com.admin_expenses.admin_expenses.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class UserCreateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastname;

    @NotBlank(message = "La contraseña es obligatorio")
    private String password;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @NotBlank(message = "El rol es obligatorio")
    private Long roleId;

    public UserCreateDTO(String name, String email, String lastname, Long roleId) {
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.roleId = roleId;
    }
}
