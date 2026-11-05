package org.kalinajug.projekat_rsw.controllers;

import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.services.ZadatakZaIzraduService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/zadacizaizradu")
@RequiredArgsConstructor
public class ZadatakZaIzraduController {
    private final ZadatakZaIzraduService zadatakZaIzraduService;
}
