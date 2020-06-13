package com.az.io.movieapi.service.impl;

import com.az.io.movieapi.model.Cast;
import com.az.io.movieapi.model.Crew;
import com.az.io.movieapi.repo.CastRepo;
import com.az.io.movieapi.repo.CrewRepo;
import com.az.io.movieapi.service.CreditsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditServiceImpl implements CreditsService {

    private final CastRepo castRepo;
    private final CrewRepo crewRepo;

    @Override
    public void saveCrew(List<Crew> crews) {
        crewRepo.saveAll(crews);
    }

    @Override
    public void saveCast(List<Cast> casts) {
        castRepo.saveAll(casts);
    }
}
