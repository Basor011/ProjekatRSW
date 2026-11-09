package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.services.AuthService;


import org.kalinajug.projekat_rsw.services.StudentPreuzimanjeService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preuzimanje")
@RequiredArgsConstructor
public class PreuzimanjeController {

    private final StudentPreuzimanjeService preuzimanjeService;

    @GetMapping
    public ResponseEntity<Resource> preuzmiZadatak(@AuthenticationPrincipal Jwt jwt,
                                                   @RequestParam Long terminId, @RequestParam Integer brGrupe)
    {
        Long studentId = ((Number) jwt.getClaim(AuthService.CLAIM_KORISNIK_ID)).longValue();
        StudentPreuzimanjeService.Preuzimanje rezultat = preuzimanjeService.preuzmi(studentId, terminId, brGrupe);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rezultat.naziv() + "\"")
                .body(rezultat.file());
    }
}