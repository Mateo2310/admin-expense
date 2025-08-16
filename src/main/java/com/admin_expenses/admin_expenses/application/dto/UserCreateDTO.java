package com.admin_expenses.admin_expenses.application.dto;

import com.admin_expenses.admin_expenses.domain.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserCreateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String email;
    private String lastname;
    private String roleName;

    public UserCreateDTO(String name, String email, String lastname, String roleName) {
        this.name = name;
        this.email = email;
        this.lastname = lastname;
        this.roleName = roleName;
    }
}
