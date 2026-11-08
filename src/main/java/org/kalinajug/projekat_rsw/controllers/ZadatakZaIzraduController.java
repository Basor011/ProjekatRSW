package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.ZadatakZaIzraduRequest;
import org.kalinajug.projekat_rsw.controllers.response.ZadatakZaIzraduResponse;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.kalinajug.projekat_rsw.services.ZadatakZaIzraduService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
            @RequestParam("grupaId") Long grupaId,
            @RequestParam("nastavnikId") Long nastavnikId,
            @RequestParam("naziv") String naziv,
            @RequestParam(value = "opis", required = false) String opis,
            @RequestParam("file") MultipartFile file
    ) throws IOException {


        ZadatakZaIzradu zadatakZaIzradu = zadatakZaIzraduService.save(grupaId, nastavnikId, naziv, opis, file);

        ZadatakZaIzraduResponse zadatakZaIzraduResponse = Converters.toZadatakZaIzraduResponse(zadatakZaIzradu);
        return ResponseEntity.status(HttpStatus.CREATED).body(zadatakZaIzraduResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> izbrisiZadatakZaIzradu(@PathVariable Long id) {
        zadatakZaIzraduService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
