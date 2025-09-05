package com.backend.event_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @PostMapping("/sign-in")
    public ResponseEntity<?> login() {
        return null;
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return null;
    }
}
