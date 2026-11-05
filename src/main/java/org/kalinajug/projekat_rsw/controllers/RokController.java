package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.RokRequest;
import org.kalinajug.projekat_rsw.controllers.response.RokResponse;
import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.services.RokService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rokovi")
@RequiredArgsConstructor
public class RokController {
    private final RokService rokService;


    @PostMapping("/add")
    public ResponseEntity<RokResponse> kreiraj(@Valid @RequestBody RokRequest request) {
        Rok snimljenRok = rokService.save(request.getNaziv(),request.getTipRoka(),request.getPredmetId());
        RokResponse response= Converters.toRokResponse(snimljenRok);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public List<RokResponse> nadjiRokovePoPredmetu(@PathVariable Long id) {
        List<Rok> rokovi =rokService.findByPredmetId(id);
        return  Converters.toList(rokovi, Converters::toRokResponse);
    }



    /*
    @GetMapping("/predmeti/{predmetId}/rokovi")
    public List<RokResponse> sviPoPredmetu(@PathVariable Long predmetId) {
        return rokService.findAll(predmetId);
    }

     */


    @DeleteMapping("/{id}")
    public void obrisi(@PathVariable Long id) {
        rokService.deleteById(id);
    }


}
