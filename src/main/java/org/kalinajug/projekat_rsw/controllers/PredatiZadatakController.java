package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.services.PredatZadatakService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predatizadaci")
@RequiredArgsConstructor
public class PredatiZadatakController {
    private final PredatZadatakService predatZadatakService;
}
