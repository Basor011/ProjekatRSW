package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.NastavnikRequest;
import org.kalinajug.projekat_rsw.controllers.response.NastavnikResponse;
import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.services.KorisnikService;
import org.kalinajug.projekat_rsw.services.NastavnikService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/nastavnik")
public class NastavnikController {
    private final NastavnikService nastavnikService;
    private final KorisnikService korisnikService;

    @PostMapping(path = "/add")
    public ResponseEntity<NastavnikResponse> addNewNastavnik(@RequestBody @Valid NastavnikRequest nastavnikRequest) {
       Nastavnik nastavnik= korisnikService.saveNastavnik(nastavnikRequest.getIme(),nastavnikRequest.getPrezime(),nastavnikRequest.getEmail(),nastavnikRequest.getLozinka());
       NastavnikResponse nastavnikResponse= Converters.toNastavnikResponse(nastavnik);
       return ResponseEntity.status(HttpStatus.CREATED).body(nastavnikResponse);
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
