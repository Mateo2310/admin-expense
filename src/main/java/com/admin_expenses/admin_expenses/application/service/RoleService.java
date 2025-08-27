package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IRolesService;
import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleService implements IRolesService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleResponseDTO> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role ->  {
            RoleResponseDTO dto = new RoleResponseDTO();
            dto.setName(role.getName());
            return dto;
        }).toList();
    }

    @Override
    public String create(RoleCreateDTO dto) {
        Role newRole = new Role();
        newRole.setName(dto.getName());
        newRole.setCreatedAt(new Date());
        newRole.setUpdatedAt(new Date());
        Role savedRole = roleRepository.save(newRole);
        RoleResponseDTO dtoResponse = new RoleResponseDTO();
        dtoResponse.setName(savedRole.getName());
        return "";
    }

    @Override
    public String delete(String name) {
        return "";
    }

    @Override
    public String update(RoleCreateDTO dto) {
        return "";
    }

    public String deleteById(Long id) {
        this.roleRepository.deleteById(id);
        return "";
    }

    @Override
    public RoleResponseDTO findById(Long id) {
        return null;
    }
}
