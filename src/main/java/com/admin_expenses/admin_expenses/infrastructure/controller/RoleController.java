package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IRolesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final IRolesService iRolesService;

    public RoleController(IRolesService iRolesService) {
        this.iRolesService = iRolesService;
    }

    @PostMapping
    public ResponseEntity<String> createRole(@RequestBody RoleCreateDTO roleCreateDTO) {
        String dto = iRolesService.create(roleCreateDTO);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> findAll() {
        List<RoleResponseDTO> roles = iRolesService.findAll();
        return ResponseEntity.ok().body(roles);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRole(@RequestParam Integer id) {
        this.iRolesService.deleteById(Long.valueOf(id));
        return ResponseEntity.ok().build();
    }
}
