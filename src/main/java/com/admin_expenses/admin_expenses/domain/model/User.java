package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String username;
    private String lastname;
    private Role role;
    private Date createdAt;
    private Date updatedAt;

    public User() {
    }

    public User(String name, String username, String lastname, Role role, Date createdAt, Date updatedAt) {
        this.name = name;
        this.username = username;
        this.lastname = lastname;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
