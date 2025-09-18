package com.admin_expenses.admin_expenses.application.service.interfaces;

import com.admin_expenses.admin_expenses.application.dto.AuthenticationResponse;
import com.admin_expenses.admin_expenses.application.dto.UserCreateDTO;
import com.admin_expenses.admin_expenses.application.dto.UserLoginDTO;

public interface IAuthenticationService {
    AuthenticationResponse register(UserCreateDTO request);
    AuthenticationResponse login(UserLoginDTO request);
}
