package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.services.PreuzimanjeService;
import org.kalinajug.projekat_rsw.services.PreuzimanjeService.Preuzimanje;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/preuzimanje")
@RequiredArgsConstructor
public class PreuzimanjeController {

    private final PreuzimanjeService preuzimanjeService;

    @GetMapping
    public ResponseEntity<Resource> preuzmiZadatak(
            @RequestParam Long studentId,
            @RequestParam Long terminId,
            @RequestParam Integer brGrupe
    ) {
        Preuzimanje rezultat = preuzimanjeService.preuzmi(studentId, terminId, brGrupe);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + rezultat.naziv() + "\"")
                .body(rezultat.file());
    }
}