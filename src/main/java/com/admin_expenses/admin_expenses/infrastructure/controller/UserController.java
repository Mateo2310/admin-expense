package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.ResponseGeneric;
import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<UserDTO> users = this.userService.findAll();
        return ResponseEntity.ok(new ResponseGeneric<List<UserDTO>>("success", "Users", users){});
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        UserDTO userDTO = this.userService.findById(id);
        return ResponseEntity.ok(new ResponseGeneric<UserDTO>("success", "User", userDTO){});
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        this.userService.deleteById(id);
        return ResponseEntity.ok().body(new ResponseGeneric<>("success", "User deleted successfully", null));
    }
}
