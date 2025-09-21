package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserResponseDTO;
import com.admin_expenses.admin_expenses.domain.model.UserModel;

import java.util.List;

public interface IUserService {
    UserModel findByUsername(String username);
    String create(UserCreateDTO dto);
    String deleteById(Long id);
    UserResponseDTO findById(Long id);
    List<UserResponseDTO> findAll();
}
