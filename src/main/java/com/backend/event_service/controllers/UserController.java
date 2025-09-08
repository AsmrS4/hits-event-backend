package com.backend.event_service.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @GetMapping("/me")
    public ResponseEntity<?> getProfile() {
        return null;
    }
    @PutMapping("/me")
    public ResponseEntity<?> editProfile() {
        return null;
    }
}
