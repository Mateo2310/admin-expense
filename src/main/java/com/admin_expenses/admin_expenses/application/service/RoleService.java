package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IRolesService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.RoleModel;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleService implements IRolesService {
    private final RoleRepository roleRepository;

    @Override
    public List<RoleResponseDTO> findAll() {
        try {
            List<RoleModel> roleModels = roleRepository.findAll();
            return roleModels.stream().map(role ->  {
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
        RoleModel newRoleModel = new RoleModel();
        newRoleModel.setName(dto.getName());
        try {
            this.roleRepository.save(newRoleModel);
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
            RoleModel roleModel = this.roleRepository.findById(id);
            if (roleModel == null) {
                throw new RoleNotFoundException(id);
            }
            RoleResponseDTO dto = new RoleResponseDTO();
            dto.setName(roleModel.getName());

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
