package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Company;
import com.az.io.movieapi.repo.CompanyRepo;
import com.az.io.movieapi.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepo repo;

    @Override
    public void addCompanies(Company company) {
        repo.save(company);
    }

    @Override
    public void addCompanyList(List<Company> companies) {
        repo.saveAll(companies);
    }
}
