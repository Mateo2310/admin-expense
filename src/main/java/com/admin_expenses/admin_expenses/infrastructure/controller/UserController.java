package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserResponseDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody UserCreateDTO userCreateDTO) {
        return ResponseEntity.ok(this.userService.create(userCreateDTO));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserResponseDTO> users = this.userService.findAll();
        return ResponseEntity.ok(users);
    }
}
