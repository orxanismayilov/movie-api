package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HistoryRepo extends JpaRepository<History,Integer> {

    Optional<History> findByUser_Id(String userId);
}
