package com.az.io.movieapi.repo;

import com.az.io.movieapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {
}
