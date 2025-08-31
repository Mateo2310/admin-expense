package com.admin_expenses.admin_expenses.infrastructure.controller.auth;

import com.admin_expenses.admin_expenses.infrastructure.controller.auth.dto.AuthenticationRequest;
import com.admin_expenses.admin_expenses.infrastructure.controller.auth.dto.AuthenticationResponse;
import com.admin_expenses.admin_expenses.infrastructure.controller.auth.dto.RegisterRequest;
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

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {

    }

    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){}
}
