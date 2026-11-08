package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.kalinajug.projekat_rsw.services.PredajaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/predaja")
@RequiredArgsConstructor
public class PredajaController {

    private final PredajaService predajaService;

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PredatiZadatak> predajZadatak(
            @RequestParam Long studentId,
            @RequestParam Long zadatakId,
            @RequestParam("resenje") MultipartFile resenje
    ) throws IOException {
        PredatiZadatak sacuvan = predajaService.predaj(studentId, zadatakId, resenje);
        return ResponseEntity.status(HttpStatus.CREATED).body(sacuvan);
    }
}