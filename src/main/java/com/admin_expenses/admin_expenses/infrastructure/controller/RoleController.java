package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;
import com.admin_expenses.admin_expenses.application.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@RequestBody RoleCreateDTO roleCreateDTO) {
        RoleResponseDTO dto = roleService.createRole(roleCreateDTO);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> findAll() {
        List<RoleResponseDTO> roles = roleService.findAll();
        return ResponseEntity.ok().body(roles);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRole(@RequestParam Integer id) {
        this.roleService.deleteById(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}
