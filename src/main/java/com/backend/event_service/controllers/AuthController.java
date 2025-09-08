package com.backend.event_service.controllers;

import com.backend.event_service.dto.AccessToken;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.dto.SignInRequest;
import com.backend.event_service.dto.SignUpRequest;
import com.backend.event_service.services.auth.AuthService;
import com.backend.event_service.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/sign-in")
    public ResponseEntity<AccessToken> login(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @PostMapping("/sign-up")
    public ResponseEntity<RequestResponse> registryUser(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }
    @PostMapping("/logout")
    public ResponseEntity<RequestResponse> logout() {
        //TODO:добавить blacklist
        return null;
    }
}
