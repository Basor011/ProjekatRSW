package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.response.PredatiZadatakResponse;
import org.kalinajug.projekat_rsw.services.AuthService;
import org.kalinajug.projekat_rsw.services.OcenjivanjeService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moja-resenja")
@RequiredArgsConstructor
public class MojaResenjaController {
    private final OcenjivanjeService service;

    @GetMapping("/{predatiId}")
    public PredatiZadatakResponse moje(@AuthenticationPrincipal Jwt jwt, @PathVariable Long predatiId) {
        Long studentId = ((Number) jwt.getClaim(AuthService.CLAIM_KORISNIK_ID)).longValue();
        return Converters.toPredatiZadatakResponse(service.findMoje(predatiId, studentId));
    }
}