package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.ZadatakZaIzraduRequest;
import org.kalinajug.projekat_rsw.controllers.response.ZadatakZaIzraduResponse;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.kalinajug.projekat_rsw.services.ZadatakZaIzraduService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zadacizaizradu")
@RequiredArgsConstructor
public class ZadatakZaIzraduController {
    private final ZadatakZaIzraduService zadatakZaIzraduService;

    @PostMapping("/add")
    public ResponseEntity<ZadatakZaIzraduResponse> addNewZadatakZaIzradu(@RequestBody @Valid ZadatakZaIzraduRequest zadatakZaIzraduRequest)
    {
        ZadatakZaIzradu zadatakZaIzradu= zadatakZaIzraduService.save(zadatakZaIzraduRequest.getGrupaId(),zadatakZaIzraduRequest.getNastavnikId());

        ZadatakZaIzraduResponse zadatakZaIzraduResponse= Converters.toZadatakZaIzraduResponse(zadatakZaIzradu);
        return ResponseEntity.status(HttpStatus.CREATED).body(zadatakZaIzraduResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> izbrisiZadatakZaIzradu(@PathVariable Long id)
    {
        zadatakZaIzraduService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
