package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserResponseDTO;
import com.admin_expenses.admin_expenses.domain.model.User;

public interface IUserService extends IServiceGeneric<UserResponseDTO, UserCreateDTO>{
    public User findByUsername(String username);
}
