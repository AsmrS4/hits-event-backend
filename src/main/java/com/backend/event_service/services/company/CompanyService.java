package com.backend.event_service.services.company;

import com.backend.event_service.dto.company.CompanyDTO;
import com.backend.event_service.dto.company.CompanyRequest;
import com.backend.event_service.dto.RequestResponse;

import java.util.List;

public interface CompanyService {
    List<CompanyDTO> getCompanies();
    RequestResponse createCompany(CompanyRequest request);
}
