package com.az.io.movieapi.service;

import com.az.io.movieapi.model.Cast;
import com.az.io.movieapi.model.Crew;

import java.util.List;

public interface CreditsService {

    void saveCrew(List<Crew> crews);

    void saveCast(List<Cast> casts);
}
