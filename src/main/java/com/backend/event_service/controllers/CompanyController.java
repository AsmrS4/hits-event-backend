package com.backend.event_service.controllers;

import com.backend.event_service.dto.company.CompanyRequest;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.services.company.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;
    @GetMapping
    public ResponseEntity<?> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }
    @PostMapping
    public ResponseEntity<RequestResponse> createCompany(@RequestBody @Valid CompanyRequest request) {
        return ResponseEntity.ok(companyService.createCompany(request));
    }

}
