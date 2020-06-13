package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company,Integer> {
}
