package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class FinantialInstitute {
    private Long id;
    private String name;
    private String type;
    private Date createdAt;
    private Date updatedAt;
    private User createdBy;

    public FinantialInstitute(String name, String type, User createdBy, Date createdAt, Date updatedAt) {
        this.name = name;
        this.type = type;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public FinantialInstitute() {
    }
}
