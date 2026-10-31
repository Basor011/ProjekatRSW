package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Student extends Korisnik {
    private String brIndeksa;
    private String smer;
    private Integer godUpisa;

    @OneToMany
    private List<PredatiZadatak>  predatiZadatak;

    @OneToMany
    private List<ListaPrijavljenih> predatiZadatakList;
}
