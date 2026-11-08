package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Student extends Korisnik {
    private Integer brIndeksa;
    private String smer;
    private Integer godinaUpisa;

    @OneToMany(mappedBy = "student")
    private List<PredatiZadatak>  predatiZadatak;

    @OneToMany(mappedBy = "student")
    private List<ListaPrijavljenih> listePrijavljenih;

    @Override
    public TipKorisnika getTipKorisnika() {
        return TipKorisnika.STUDENT;
    }
}
