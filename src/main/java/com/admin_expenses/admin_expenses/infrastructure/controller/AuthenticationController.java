package com.admin_expenses.admin_expenses.infrastructure.controller;

import com.admin_expenses.admin_expenses.application.dto.AuthenticationResponse;
import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserLoginDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserCreateDTO request) {
        return ResponseEntity.ok(this.authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody UserLoginDTO userLoginDTO){
        return ResponseEntity.ok(this.authenticationService.login(userLoginDTO));
    }
}
