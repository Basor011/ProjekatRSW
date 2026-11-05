package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.kalinajug.projekat_rsw.controllers.request.GrupaRequest;
import org.kalinajug.projekat_rsw.controllers.response.GrupaResponse;
import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.services.GrupaService;
import org.kalinajug.projekat_rsw.services.TerminService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grupe")
@RequiredArgsConstructor
public class GrupaController {
    private final GrupaService grupaService;
    private final TerminService terminService;

    @PostMapping(path="/add")
    public ResponseEntity<GrupaResponse> addNewGrupa(@RequestBody @Valid GrupaRequest grupaRequest)
    {
        Grupa snimljenaGrupa = grupaService.save(grupaRequest.getTerminId(), grupaRequest.getBrojGrupe());

        GrupaResponse response = Converters.toGrupaResponse(snimljenaGrupa);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(path="/termin/{id}")
    public List<GrupaResponse> getGrupeByTerminId(@PathVariable Long id)
    {
        List<Grupa> grupe=grupaService.findByTerminId(id);
        //return Converters.toList(Converters.toGrupaResponse(grupe));
        return Converters.toList(grupe, Converters::toGrupaResponse);
    }
}
