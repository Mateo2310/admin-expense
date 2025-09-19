package com.admin_expenses.admin_expenses.domain.model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String name;
    private String username;
    private String lastname;
    private String password;
    private RoleModel roleModel;

    public UserModel(Long id, String name, String username, String lastname, RoleModel roleModel, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.lastname = lastname;
        this.roleModel = roleModel;
    }
}
