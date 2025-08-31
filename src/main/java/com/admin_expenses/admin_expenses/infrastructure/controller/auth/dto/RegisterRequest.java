package com.admin_expenses.admin_expenses.infrastructure.controller.auth.dto;

import com.admin_expenses.admin_expenses.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String name;
    private String username;
    private String lastname;
    private String password;
    private Role role;
}
