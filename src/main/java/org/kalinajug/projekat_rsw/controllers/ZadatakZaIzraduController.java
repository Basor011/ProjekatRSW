package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.response.ZadatakZaIzraduResponse;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.kalinajug.projekat_rsw.services.AuthService;
import org.kalinajug.projekat_rsw.services.ZadatakZaIzraduService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/zadacizaizradu")
@RequiredArgsConstructor
public class ZadatakZaIzraduController {
    private final ZadatakZaIzraduService zadatakZaIzraduService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ZadatakZaIzraduResponse> addNewZadatakZaIzradu(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam("grupaId") Long grupaId,
            @RequestParam("naziv") String naziv,
            @RequestParam(value = "opis", required = false) String opis,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Long nastavnikId = ((Number) jwt.getClaim(AuthService.CLAIM_KORISNIK_ID)).longValue();
        ZadatakZaIzradu z = zadatakZaIzraduService.save(grupaId, nastavnikId, naziv, opis, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(Converters.toZadatakZaIzraduResponse(z));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> izbrisiZadatakZaIzradu(@PathVariable Long id) {
        zadatakZaIzraduService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}