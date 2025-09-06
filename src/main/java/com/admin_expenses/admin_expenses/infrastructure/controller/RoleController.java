package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.dto.RoleCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.RoleResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IRolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final IRolesService iRolesService;

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleCreateDTO roleCreateDTO) {
        String dto = iRolesService.create(roleCreateDTO);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Role created", dto));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<RoleResponseDTO> roles = iRolesService.findAll();
        return ResponseEntity.ok().body(new  ResponseGeneric<>("success", "Roles", roles));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Long id) {
        this.iRolesService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "Role deleted succesfully", null));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRole(@PathVariable Long id) {
        RoleResponseDTO role = iRolesService.findById(id);
        return ResponseEntity.ok().body(new  ResponseGeneric<>("success", "Role", role));
    }
}
