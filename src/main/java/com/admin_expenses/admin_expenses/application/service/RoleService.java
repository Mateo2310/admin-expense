package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IRolesService;
import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        this.delete(name);
        return "SUCCESS";
    }

    @Override
    public String update(RoleCreateDTO dto) {
        Optional<Role> roleFinded = this.roleRepository.findByName(dto.getName());
        if (roleFinded.isEmpty()) {
            return "Role not found";
        }
        Role role = roleFinded.get();
        role.setUpdatedAt(new Date());
        role.setName(dto.getName());
        return "SUCESS";
    }

    public String deleteById(Long id) {
        this.roleRepository.deleteById(id);
        return "SUCESS";
    }

    @Override
    public RoleResponseDTO findById(Long id) {
        Optional<Role> role = this.roleRepository.findById(id);
        if (role.isEmpty()) {
            return null;
        }
        RoleResponseDTO dto = new RoleResponseDTO();
        dto.setName(role.get().getName());

        return dto;
    }
}
