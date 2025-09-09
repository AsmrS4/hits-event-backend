package com.backend.event_service.services.company;

import com.backend.event_service.dto.CompanyDTO;
import com.backend.event_service.dto.CompanyRequest;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.entities.Company;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getCompanies();
    RequestResponse createCompany(CompanyRequest request);
}
