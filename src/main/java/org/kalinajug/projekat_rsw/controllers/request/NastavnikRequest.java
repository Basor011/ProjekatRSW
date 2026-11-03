package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;

import java.util.List;
@Data
public class NastavnikRequest {


    @NotNull
    private Long korisnikid;
    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;
    @NotBlank
    private String email;
    @NotBlank
    private String lozinka;
    private List<ZadatakZaIzradu> zadaciZaIzradu;
}
