package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.Tv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvRepo extends JpaRepository<Tv,String> {
}
