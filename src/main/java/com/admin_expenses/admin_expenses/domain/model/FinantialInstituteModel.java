package com.admin_expenses.admin_expenses.domain.model;

import lombok.*;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class FinantialInstituteModel {
    private Long id;
    private String name;
    private String type;
    private UserModel createdBy;
}
