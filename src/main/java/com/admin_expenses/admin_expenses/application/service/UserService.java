package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IUserService;
import com.admin_expenses.admin_expenses.domain.exception.*;
import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.domain.model.User;
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

import java.util.Date;
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
            Role role = this.roleRepository.findByName(creationDTO.getRoleName());
            if (role == null) {
                throw new RoleNotFoundException(creationDTO.getRoleName());
            }
            User newUser = new User();
            newUser.setName(creationDTO.getName());
            newUser.setLastname(creationDTO.getLastname());
            newUser.setUsername(creationDTO.getEmail());
            newUser.setPassword(this.passwordEncoder.encode(creationDTO.getPassword()));
            newUser.setRole(role);
            newUser.setCreatedAt(new Date());
            newUser.setUpdatedAt(new Date());

            // 3. Llamar al repositorio de Dominio para guardar
            User userSaved = this.userRepository.save(newUser);
            return this.jwtService.generateToken(new UserDetailsImpl(userSaved));
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
    public String delete(String name) {
        try {
            User userFinded = this.userRepository.findByUsername(name);
            if (userFinded == null) {
                throw new UserNotFoundException(name);
            }
            this.userRepository.delete(userFinded);
            return "SUCESS";
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException("Violación de integridad al guardar el usuario", e);
        } catch (CannotCreateTransactionException cctex) {
            throw new DatabaseUnavailableException("No se pudo conectar con la base de datos", cctex);
        } catch (Exception e) {
            throw new UnexpectedException("Error inesperado al guardar el usuario", e);
        }
    }

    @Override
    public String update(UserCreateDTO dto) {
        try {
            User user = this.userRepository.findByUsername(dto.getName());
            Role roleFinded = this.roleRepository.findByName(dto.getRoleName());
            if (user == null) {
                throw new UserNotFoundException(dto.getName());
            }
            if (roleFinded == null) {
                throw new RoleNotFoundException(dto.getRoleName());
            }
            user.setLastname(dto.getLastname());
            user.setUsername(dto.getEmail());
            user.setUpdatedAt(new Date());
            user.setRole(roleFinded);

            this.userRepository.update(user);
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
    public UserDTO findById(Long id) {
        try {
            // 1. Llamar al repositorio de Dominio para obtener
            User user = userRepository.findById(id);
            if (user == null) {
                throw new UserNotFoundException(id);
            }
            UserDTO responseDTO = new UserDTO();
            responseDTO.setId(user.getId());
            responseDTO.setEmail(user.getUsername());
            responseDTO.setName(user.getName());
            responseDTO.setLastName(user.getLastname());
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
    public List<UserDTO> findAll() {
        try {
            List<User> users = userRepository.findAll();
            return users.stream().map(user -> {
                UserDTO dto = new UserDTO();
                dto.setId(user.getId());
                dto.setEmail(user.getUsername());
                dto.setName(user.getName());
                dto.setLastName(user.getLastname());
                dto.setRole(user.getRole().getName());
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
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
