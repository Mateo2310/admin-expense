package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IRolesService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

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
        try {
            List<Role> roles = roleRepository.findAll();
            return roles.stream().map(role ->  {
                RoleResponseDTO dto = new RoleResponseDTO();
                dto.setName(role.getName());
                return dto;
            }).toList();
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el rol", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el rol", e);
        }
    }

    @Override
    public String create(RoleCreateDTO dto) {
        Role newRole = new Role();
        newRole.setName(dto.getName());
        newRole.setCreatedAt(new Date());
        newRole.setUpdatedAt(new Date());
        try {
            this.roleRepository.save(newRole);
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el rol", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el rol", e);
        }
    }

    @Override
    public String delete(String name) {
        return null;
    }

    @Override
    public String update(RoleCreateDTO dto) {
        try {
            Role role = this.roleRepository.findByName(dto.getName());
            if (role == null) {
                throw new RoleNotFoundException(dto.getName());
            }
            role.setUpdatedAt(new Date());
            role.setName(dto.getName());
            this.roleRepository.save(role);
            return "SUCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el rol", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el rol", e);
        }
    }

    public String deleteById(Long id) {
        try {
            this.roleRepository.deleteById(id);
            return "SUCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el rol", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el rol", e);
        }
    }

    @Override
    public RoleResponseDTO findById(Long id) {
        try {
            Role role = this.roleRepository.findById(id);
            if (role == null) {
                throw new RoleNotFoundException(id);
            }
            RoleResponseDTO dto = new RoleResponseDTO();
            dto.setName(role.getName());

            return dto;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el rol", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el rol", e);
        }
    }
}
