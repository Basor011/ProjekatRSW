package org.kalinajug.projekat_rsw.controllers;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.kalinajug.projekat_rsw.controllers.request.PredmetRequest;
import org.kalinajug.projekat_rsw.controllers.response.GrupaResponse;
import org.kalinajug.projekat_rsw.controllers.response.PredmetResponse;
import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.model.Predmet;
import org.kalinajug.projekat_rsw.services.PredmetService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/predmet")
public class PredmetController {
    private final PredmetService predmetService;

    @GetMapping(path = "/all")
    public List<PredmetResponse> getPredmeti(){
        return Converters.toList(predmetService.findAll(), Converters::toPredmetResponse);
    }
    @PostMapping(path="/add")
    public ResponseEntity<PredmetResponse> addNewPredmet(@RequestBody @Valid PredmetRequest predmetRequest)
    {
        Predmet snimljenpredmet = predmetService.save(predmetRequest.getNaziv());

        PredmetResponse response = Converters.toPredmetResponse(snimljenpredmet);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
