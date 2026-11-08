package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.ListaPrijavljenihRequest;
import org.kalinajug.projekat_rsw.controllers.response.ListaPrijavljenihResponse;
import org.kalinajug.projekat_rsw.model.ListaPrijavljenih;
import org.kalinajug.projekat_rsw.services.ListaPrijavljenihService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/listaprijavljenih")
@RequiredArgsConstructor
public class ListaPrijavljenihController {

    private final ListaPrijavljenihService listaPrijavljenihService;


    @PostMapping("/add")
    public ResponseEntity<ListaPrijavljenihService.RezultatUnosa> unesiSpisak(@RequestBody @Valid ListaPrijavljenihRequest request) {
        ListaPrijavljenihService.RezultatUnosa rezultat = listaPrijavljenihService.unesiSpisak(request.getTerminId(), request.getEmailovi());
        return ResponseEntity.ok(rezultat);
    }


}
