package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Cast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CastRepo extends JpaRepository<Cast,Integer> {
}
