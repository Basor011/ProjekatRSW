package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.kalinajug.projekat_rsw.model.Nastavnik;

import java.util.List;

@Data
public class ZadatakZaIzraduRequest {

    @NotBlank
    private String naziv;
    @NotBlank
    private String originalniNaziv;
    private String opis;
    @NotBlank
    private String zadatakPath;

    @NotNull
    private Long nastavnikId;
    @NotNull
    private Long grupaId;
    private List<Long> predatiZadaciId;

}