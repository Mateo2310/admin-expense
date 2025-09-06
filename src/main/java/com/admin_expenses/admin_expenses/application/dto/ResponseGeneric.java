package com.admin_expenses.admin_expenses.application.dto;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGeneric<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String status;
    private String message;
    private T data;
}
