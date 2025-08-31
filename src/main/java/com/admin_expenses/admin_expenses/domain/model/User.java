package com.admin_expenses.admin_expenses.domain.model;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String username;
    private String lastname;
    private String password;
    private Role role;
    private Date createdAt;
    private Date updatedAt;

    public User(Long id, String name, String username, String lastname, Role role, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.lastname = lastname;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
