package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.KomentarRequest;
import org.kalinajug.projekat_rsw.controllers.response.KomentarResponse;
import org.kalinajug.projekat_rsw.controllers.response.PredatiZadatakResponse;
import org.kalinajug.projekat_rsw.model.Komentar;
import org.kalinajug.projekat_rsw.services.AuthService;
import org.kalinajug.projekat_rsw.services.OcenjivanjeService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ocenjivanje")
@RequiredArgsConstructor
public class OcenjivanjeController {
    private final OcenjivanjeService service;

    @PostMapping("/komentar")
    public ResponseEntity<KomentarResponse> dodaj(@RequestBody @Valid KomentarRequest r) {
        Komentar k = service.saveKomentar(r.getPredatiZadatakId(), r.getPath(),
                r.getPocetniRed(), r.getKrajnjiRed(), r.getKomentar());
        return ResponseEntity.status(HttpStatus.CREATED).body(Converters.toKomentarResponse(k));
    }

    @PutMapping("/{predatiId}/poeni")
    public PredatiZadatakResponse poeni(@PathVariable Long predatiId, @RequestParam Double poeni) {
        return Converters.toPredatiZadatakResponse(service.savePoene(predatiId, poeni));
    }

    @GetMapping("/{predatiId}/komentari")
    public List<KomentarResponse> komentari(@PathVariable Long predatiId) {
        return Converters.toList(service.findKomentarByPredatiZadatakId(predatiId), Converters::toKomentarResponse);
    }

    @DeleteMapping("/komentar/{id}")
    public ResponseEntity<Void> obrisi(@PathVariable Long id) {
        service.deleteKomentar(id);
        return ResponseEntity.noContent().build();
    }
}

