package com.az.io.movieapi.controller;

import com.az.io.movieapi.model.Cast;
import com.az.io.movieapi.model.Crew;
import com.az.io.movieapi.service.CreditsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("credits")
@RequiredArgsConstructor
public class CreditsController {

    private final CreditsService creditsService;

    @PostMapping("/cast/list")
    public void addCastList(@RequestBody List<Cast> casts) {
        creditsService.saveCast(casts);
    }

    @PostMapping("/crew/list")
    public void addCrewList(@RequestBody List<Crew> crews) {
        creditsService.saveCrew(crews);
    }
}
