package com.admin_expenses.admin_expenses.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String lastName;
    private String email;
    private String role;

    public UserResponseDTO() {
    }
}
