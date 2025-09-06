package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserDTO;
import com.admin_expenses.admin_expenses.domain.model.User;

public interface IUserService extends IServiceGeneric<UserDTO, UserCreateDTO>{
    User findByUsername(String username);
}
