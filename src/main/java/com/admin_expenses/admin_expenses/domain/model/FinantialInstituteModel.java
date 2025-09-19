package com.admin_expenses.admin_expenses.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FinantialInstituteModel {
    private Long id;
    private String name;
    private String type;
    private UserModel createdBy;
}
