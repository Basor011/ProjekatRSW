package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.TerminRequest;
import org.kalinajug.projekat_rsw.controllers.response.StudentResponse;
import org.kalinajug.projekat_rsw.controllers.response.TerminResponse;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.services.TerminService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/termini")
@RequiredArgsConstructor
public class TerminController {
    private final TerminService terminService;

    @PostMapping("/add")
    public ResponseEntity<TerminResponse> dodajTermin(@RequestBody @Valid TerminRequest terminRequest)
    {
        Termin termin  = terminService.save(terminRequest.getRokId(),terminRequest.getPocetak(),terminRequest.getKraj());

        TerminResponse terminResponse= Converters.toTerminResponse(termin);

        return ResponseEntity.status(HttpStatus.CREATED).body(terminResponse);
    }
    @GetMapping("/all")
    public List<TerminResponse> getAllTermin() {
        return Converters.toList(terminService.findAll(), Converters::toTerminResponse);
    }
}
