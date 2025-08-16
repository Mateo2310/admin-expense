package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Role {
    private Long id;
    private String name;
    private Date createdAt;
    private Date updatedAt;

    public Role() {
    }
}
