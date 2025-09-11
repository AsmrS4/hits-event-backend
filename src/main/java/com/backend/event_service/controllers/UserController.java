package com.backend.event_service.controllers;

import com.backend.event_service.dto.user.UserDTO;
import com.backend.event_service.dto.user.UserEditDTO;
import com.backend.event_service.services.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getProfile() {
        return ResponseEntity.ok(userService.getProfile());
    }
    @PutMapping("/me")
    public ResponseEntity<UserDTO> editProfile(@RequestBody @Valid UserEditDTO dto) {
        return ResponseEntity.ok(userService.editProfile(dto));
    }
    @GetMapping("/confirmation/list")
    public ResponseEntity<List<UserDTO>> getConfirmationList() {
        return ResponseEntity.ok(userService.getRegistrationList());
    }
    @PostMapping("/confirmation/confirm/{id}")
    public ResponseEntity<?> confirmUserAccount(@PathVariable Long id) {
        return ResponseEntity.ok(userService.confirmUserAccount(id));
    }
    @DeleteMapping("/confirmation/reject/{id}")
    public ResponseEntity<?> rejectUserAccount(@PathVariable Long id) {
        return ResponseEntity.ok(userService.rejectUserAccount(id));
    }
}
