package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IUserService;
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
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public String create(UserCreateDTO creationDTO) {
        // 1. Verificar que exista el rol
        Role role = this.roleRepository.findByName(creationDTO.getRoleName());
        // Si el rol no se encuentra, lanzar una excepción
        if (role.isEmpty()) {
            throw new IllegalArgumentException("El rol '" + creationDTO.getRoleName() + "' especificado no existe.");
            // O una excepción personalizada más específica, por ejemplo:
            // throw new RoleNotFoundException("El rol especificado no existe.");
        }
        // 2. Mapear DTO a entidad de Dominio
        System.out.println("Role name: " + role.getName());
        User newUser = new User();
        newUser.setName(creationDTO.getName());
        newUser.setLastname(creationDTO.getLastname());
        newUser.setUsername(creationDTO.getEmail());
        newUser.setRole(role);
        newUser.setCreatedAt(new Date());
        newUser.setUpdatedAt(new Date());

        // 3. Llamar al repositorio de Dominio para guardar
        User savedUser = userRepository.save(newUser);
        if (savedUser == null) {
            return "ERROR";
        }

        return "SUCESS";
    }

    @Override
    public String delete(String name) {
        User userFinded = this.userRepository.findByUsername(name);
        if (userFinded.isPresent()) {
            this.userRepository.delete(userFinded.get());
            return "SUCESS";
        }
        return "ERROR";
    }

    @Override
    public String update(UserCreateDTO dto) {
        User userFinded = this.userRepository.findByUsername(dto.getName());
        Optional<Role> roleFinded = this.roleRepository.findByName(dto.getRoleName());

        if (userFinded.isEmpty() ||  roleFinded.isEmpty()) {
            return "ERROR";
        }

        User user = userFinded.get();
        user.setLastname(dto.getLastname());
        user.setUsername(dto.getEmail());
        user.setUpdatedAt(new Date());
        user.setRole(roleFinded.get());

        this.userRepository.update(user);
        return "SUCCESS";
    }

    @Override
    public String deleteById(Long id) {
        this.userRepository.deleteById(id);
        return "SUCCESS";
    }

    @Override
    public UserResponseDTO findById(Long id) {
        // 1. Llamar al repositorio de Dominio para obtener
        User userOptional = userRepository.findById(id);

        if (userOptional.isEmpty()) {
            return null;
        }

        UserResponseDTO responseDTO = new UserResponseDTO();
        User user = userOptional.get();
        responseDTO.setEmail(user.getUsername());
        responseDTO.setName(user.getName());
        responseDTO.setLastName(user.getLastname());
        return responseDTO;
    }

    @Override
    public List<UserResponseDTO> findAll() {
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
