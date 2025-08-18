package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserResponseDTO;
import com.admin_expenses.admin_expenses.domain.model.Role;
import com.admin_expenses.admin_expenses.domain.model.User;
import com.admin_expenses.admin_expenses.domain.repository.RoleRepository;
import com.admin_expenses.admin_expenses.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public UserResponseDTO createUser(UserCreateDTO creationDTO) {
        // 1. Verificar que exista el rol
        Optional<Role> role = this.roleRepository.findByName(creationDTO.getRoleName());
        // Si el rol no se encuentra, lanzar una excepción
        if (role.isEmpty()) {
            throw new IllegalArgumentException("El rol '" + creationDTO.getRoleName() + "' especificado no existe.");
            // O una excepción personalizada más específica, por ejemplo:
            // throw new RoleNotFoundException("El rol especificado no existe.");
        }
        // 2. Mapear DTO a entidad de Dominio
        System.out.println("Role name: " + role.get().getName());
        User newUser = new User();
        newUser.setName(creationDTO.getName());
        newUser.setLastname(creationDTO.getLastname());
        newUser.setUsername(creationDTO.getEmail());
        newUser.setRole(role.get());
        newUser.setCreatedAt(new Date());
        newUser.setUpdatedAt(new Date());

        // 3. Llamar al repositorio de Dominio para guardar
        User savedUser = userRepository.save(newUser);

        // 4. Mapear entidad de Dominio a DTO de respuesta
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setEmail(savedUser.getUsername());
        responseDTO.setName(savedUser.getName());
        responseDTO.setLastName(savedUser.getLastname());
        return responseDTO;
    }

    public Optional<UserResponseDTO> getUserById(Long id) {
        // 1. Llamar al repositorio de Dominio para obtener
        Optional<User> userOptional = userRepository.findById(id);

        // 2. Mapear entidad de Dominio (si existe) a DTO de respuesta
        return userOptional.map(user -> {
            UserResponseDTO responseDTO = new UserResponseDTO();
            responseDTO.setEmail(user.getUsername());
            responseDTO.setName(user.getName());
            responseDTO.setLastName(user.getLastname());
            return responseDTO;
        });
    }

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> {
            UserResponseDTO dto = new UserResponseDTO();
            dto.setEmail(user.getUsername());
            dto.setName(user.getName());
            dto.setLastName(user.getLastname());
            dto.setRole(user.getRole().getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
