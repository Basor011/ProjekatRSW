package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.NastavnikRequest;
import org.kalinajug.projekat_rsw.controllers.response.NastavnikResponse;
import org.kalinajug.projekat_rsw.services.NastavnikService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/nastavnik")
public class NastavnikController {
    private final NastavnikService nastavnikService;

    @PostMapping(path = "/add")
    public Long addNewNastavnik(@RequestBody @Valid NastavnikRequest nastavnikRequest) {
        return nastavnikService.save(Converters.toNastavnik(nastavnikRequest)).getId(); ///IZMENI LOZINKU
    }

    @GetMapping(path = "/all")
    public List<NastavnikResponse> getAllNastavnik() {
        return Converters.toNastavnikResponseList(nastavnikService.findAll());
    }

    @GetMapping(path = "/{id}")
    public NastavnikResponse getNastavnikById(@PathVariable Long id) {
        return nastavnikService.findById(id).map(Converters::toNastavnikResponse).orElse(null);
    }

    @GetMapping(path = "/search")
    public List<NastavnikResponse> search(
            @RequestParam(required = false) String ime,
            @RequestParam(required = false) String prezime){

        return Converters.toNastavnikResponseList(nastavnikService.findByImeAndPrezime(ime, prezime));
    }

}
