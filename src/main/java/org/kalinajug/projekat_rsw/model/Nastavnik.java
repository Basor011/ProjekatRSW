package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Nastavnik extends Korisnik {
    @OneToMany(mappedBy = "nastavnik")
    private List<ZadatakZaIzradu> zadaciZaIzradu;

    @Override
    public TipKorisnika getTipKorisnika() {
        return TipKorisnika.NASTAVNIK;
    }
}
