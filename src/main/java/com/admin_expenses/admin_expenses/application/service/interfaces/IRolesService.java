package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;

import java.util.List;

public interface IRolesService {
    String create(RoleCreateDTO dto);
    String deleteById(Long id);
    RoleResponseDTO findById(Long id);
    List<RoleResponseDTO> findAll();
}
