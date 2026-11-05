package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.RokRequest;
import org.kalinajug.projekat_rsw.controllers.response.RokResponse;
import org.kalinajug.projekat_rsw.services.RokService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rokovi")
@RequiredArgsConstructor
public class RokController {
    private final RokService rokService;

/*
    @PostMapping("/rokovi")
    public RokResponse kreiraj(@Valid @RequestBody RokRequest request) {
        return rokService.save(Converters.toRok(request,request.getPredmetId())).getId();
    }

    @GetMapping("/rokovi/{id}")
    public RokResponse nadji(@PathVariable Long id) {
        return rokService.findByPredmetId(id);
    }

 */

    /*
    @GetMapping("/predmeti/{predmetId}/rokovi")
    public List<RokResponse> sviPoPredmetu(@PathVariable Long predmetId) {
        return rokService.findAll(predmetId);
    }

     */


    @DeleteMapping("/rokovi/{id}")
    public void obrisi(@PathVariable Long id) {
        rokService.deleteById(id);
    }


}
