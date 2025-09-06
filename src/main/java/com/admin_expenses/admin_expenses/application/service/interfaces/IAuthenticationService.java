package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.application.dto.AuthenticationRequest;
import com.admin_expenses.admin_expenses.application.dto.AuthenticationResponse;
import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;

public interface IAuthenticationService {
    AuthenticationResponse register(UserCreateDTO request);
    AuthenticationResponse login(AuthenticationRequest request);
}
