package com.az.io.movieapi.controller;

import com.az.io.movieapi.model.Company;
import com.az.io.movieapi.service.CompanyService;
import jdk.internal.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public void addCompanies(@RequestBody Company company) {
        companyService.addCompanies(company);
    }

    @PostMapping("/list")
    public void addCompanyList(@RequestBody List<Company> companies) {
        companyService.addCompanyList(companies);
    }
}
