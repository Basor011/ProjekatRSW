package org.kalinajug.projekat_rsw.controllers;


import lombok.RequiredArgsConstructor;

import org.kalinajug.projekat_rsw.services.PredmetService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/predmet")
public class PredmetController {
    private final PredmetService predmetService;
}
