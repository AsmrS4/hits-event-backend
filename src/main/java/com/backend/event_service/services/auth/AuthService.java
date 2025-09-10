package com.backend.event_service.services.auth;

import com.backend.event_service.dto.AccessToken;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.SignInRequest;
import com.backend.event_service.dto.SignUpRequest;

import javax.security.sasl.AuthenticationException;

public interface AuthService {
    AccessToken login(SignInRequest signInRequest);
    RequestResponse registerUser(SignUpRequest signUpRequest);
    RequestResponse logout() throws AuthenticationException;
}
