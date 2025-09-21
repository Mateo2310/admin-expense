package com.admin_expenses.admin_expenses.application.service;

import com.admin_expenses.admin_expenses.application.dto.AuthenticationResponse;
import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserLoginDTO;
import com.admin_expenses.admin_expenses.application.service.interfaces.IAuthenticationService;
import com.admin_expenses.admin_expenses.application.service.interfaces.IUserService;
import com.admin_expenses.admin_expenses.domain.exception.UserNotFoundException;
import com.admin_expenses.admin_expenses.domain.model.UserModel;
import com.admin_expenses.admin_expenses.infrastructure.security.JwtService;
import com.admin_expenses.admin_expenses.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthenticationResponse register(UserCreateDTO request) {
        String token = this.userService.create(request, , );
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse login(UserLoginDTO request) {
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserModel userModel = this.userService.findByUsername(request.getUsername());
        if (userModel == null){
            throw new UserNotFoundException(request.getUsername());
        }
        String token = this.jwtService.generateToken(new UserDetailsImpl(userModel));
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
