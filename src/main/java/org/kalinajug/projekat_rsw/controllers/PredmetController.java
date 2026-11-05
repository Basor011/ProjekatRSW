package org.kalinajug.projekat_rsw.controllers;


import lombok.RequiredArgsConstructor;

import org.kalinajug.projekat_rsw.controllers.response.PredmetResponse;
import org.kalinajug.projekat_rsw.services.PredmetService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
