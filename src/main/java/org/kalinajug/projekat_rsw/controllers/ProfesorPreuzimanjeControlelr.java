package org.kalinajug.projekat_rsw.controllers;


import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.services.FileStorageService;
import org.kalinajug.projekat_rsw.services.ProfesorPreuzimanjeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.core.io.Resource;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/resenja")
@RequiredArgsConstructor
public class ProfesorPreuzimanjeControlelr {

    private final ProfesorPreuzimanjeService profesorService;
    private final FileStorageService fileStorageService;


    @GetMapping
    public List<ResenjeResponse> pregled(@RequestParam Long terminId,
                                         @RequestParam(required = false) Integer brGrupe,
                                         @RequestParam(defaultValue = "true") boolean samoPredat,
                                         @RequestParam(defaultValue = "false") boolean samoNeocenjen) {
        return profesorService.pronadji(new ProfesorPreuzimanjeService.Kriterijum(terminId, brGrupe, samoPredat, samoNeocenjen))
                .stream().map(ResenjeResponse::of).toList();
    }

    @GetMapping("/preuzmi")
    public ResponseEntity<StreamingResponseBody> preuzmi(@RequestParam Long terminId,
                                                         @RequestParam(required = false) Integer brGrupe,
                                                         @RequestParam(defaultValue = "true") boolean samoPredat,
                                                         @RequestParam(defaultValue = "false") boolean samoNeocenjen) {
        List<ProfesorPreuzimanjeService.ResenjeStudenta> resenja = profesorService.pronadji(
                new ProfesorPreuzimanjeService.Kriterijum(terminId, brGrupe, samoPredat, samoNeocenjen));
        if (resenja.isEmpty()) throw new ResourceNotFoundException("Nema resenja za zadate kriterijume");

        StreamingResponseBody body = out -> profesorService.zipuj(resenja, out);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resenja-termin-" + terminId + ".zip\"")
                .body(body);
    }


    @GetMapping("/{predatiId}/preuzmi")
    public ResponseEntity<Resource> preuzmiJedno(@PathVariable Long predatiId) {
        ProfesorPreuzimanjeService.ResenjeStudenta r = profesorService.nadjiJedno(predatiId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"resenje-" + predatiId + ".zip\"")
                .body(fileStorageService.load(r.resenjePath()));
    }

    public record ResenjeResponse(Long predatiId, Long studentId, int brojGrupe, String ime, String prezime,
                                  Integer brIndeksa, LocalDateTime vremePredaje, Double poeni) {
        static ResenjeResponse of(ProfesorPreuzimanjeService.ResenjeStudenta r) {
            return new ResenjeResponse(r.predatiId(), r.studentId(), r.brojGrupe(), r.ime(), r.prezime(),
                    r.brIndeksa(), r.vremePredaje(), r.poeni());
        }
    }
}