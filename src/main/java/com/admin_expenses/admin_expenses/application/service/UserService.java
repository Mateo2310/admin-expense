package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IUserService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.RoleModel;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import com.admin_expenses.admin_expenses.infrastructure.security.JwtService;
import com.admin_expenses.admin_expenses.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.CannotCreateTransactionException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Override
    public String create(UserCreateDTO creationDTO) {
        try {
            RoleModel roleModel = this.roleRepository.findById(creationDTO.getRoleId());
            if (roleModel == null) {
                throw new RoleNotFoundException(creationDTO.getRoleId());
            }
            UserModel newUserModel = new UserModel();
            newUserModel.setName(creationDTO.getName());
            newUserModel.setLastname(creationDTO.getLastname());
            newUserModel.setUsername(creationDTO.getEmail());
            newUserModel.setPassword(this.passwordEncoder.encode(creationDTO.getPassword()));
            newUserModel.setRoleModel(roleModel);

            // 3. Llamar al repositorio de Dominio para guardar
            UserModel userModelSaved = this.userRepository.save(newUserModel);
            return this.jwtService.generateToken(new UserDetailsImpl(userModelSaved));
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el usuario", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            logger.error("Error al crear usuario: {}", e.getMessage());
            throw new UnexpectedException("Error inesperado al guardar el usuario", e);
        }
    }

    @Override
    public String deleteById(Long id) {
        try {
            this.userRepository.deleteById(id);
            return "SUCCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el usuario", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el usuario", e);
        }
    }

    @Override
    public UserResponseDTO findById(Long id) {
        try {
            // 1. Llamar al repositorio de Dominio para obtener
            UserModel userModel = userRepository.findById(id);
            if (userModel == null) {
                throw new UserNotFoundException(id);
            }
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setId(userModel.getId());
            responseDTO.setEmail(userModel.getUsername());
            responseDTO.setName(userModel.getName());
            responseDTO.setLastName(userModel.getLastname());
            return responseDTO;
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el usuario", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el usuario", e);
        }
    }

    @Override
    public List<UserResponseDTO> findAll() {
        try {
            List<UserModel> userModels = userRepository.findAll();
            return userModels.stream().map(user -> {
                UserResponseDTO dto = new UserResponseDTO();
                dto.setId(user.getId());
                dto.setEmail(user.getUsername());
                dto.setName(user.getName());
                dto.setLastName(user.getLastname());
                dto.setRole(user.getRoleModel().getName());
                return dto;
            }).collect(Collectors.toList());
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al obtener usuarios", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al obtener usuarios", e);
        }
    }

    @Override
    public UserModel findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
