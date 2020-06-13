package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordRepo extends JpaRepository<Keyword,Integer> {
}
