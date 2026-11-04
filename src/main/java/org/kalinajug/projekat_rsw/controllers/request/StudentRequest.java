package org.kalinajug.projekat_rsw.controllers.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.kalinajug.projekat_rsw.model.ListaPrijavljenih;
import org.kalinajug.projekat_rsw.model.PredatiZadatak;

import java.util.List;

@Data
public class StudentRequest {

    @NotNull
    private Long korisnikid;
    @NotBlank
    private String ime;
    @NotBlank
    private String prezime;
    private String email;
    @NotNull
    private Integer brIndeksa;
    @NotBlank
    private String smer;
    @NotNull
    private Integer godinaUpisa;
    @NotBlank
    private String lozinka;


    private List<PredatiZadatak> predatiZadatak;
    private List<ListaPrijavljenih> listePrijavljenih;
}
