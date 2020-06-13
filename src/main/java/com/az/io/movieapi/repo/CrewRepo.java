package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Crew;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewRepo  extends JpaRepository<Crew,Integer> {
}
