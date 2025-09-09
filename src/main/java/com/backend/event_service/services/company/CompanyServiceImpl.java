package com.backend.event_service.services.company;

import com.backend.event_service.dto.CompanyDTO;
import com.backend.event_service.dto.CompanyRequest;
import com.backend.event_service.dto.RequestResponse;
import com.backend.event_service.entities.Company;
import com.backend.event_service.repositories.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;
    @Override
    public List<CompanyDTO> getCompanies() {
        return companyRepository.findAll().stream().map(company -> {
            CompanyDTO dto = new CompanyDTO();
            dto.setId(company.getId());
            dto.setName(company.getName());
            dto.setCreatedAt(company.getCreatedAt());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public RequestResponse createCompany(CompanyRequest request) {
        if(companyRepository.existsByName(request.getName())) {
            throw new RuntimeException("This name already taken");
        }
        Company company = new Company();
        company.setName(request.getName());
        companyRepository.save(company);
        return new RequestResponse(true, 200, "Company " + request.getName() + " was created");
    }
}
