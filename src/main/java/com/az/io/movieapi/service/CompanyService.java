package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Company;

import java.util.List;

public interface CompanyService {

    void addCompanies(Company company);

    void addCompanyList(List<Company> companies);
}
