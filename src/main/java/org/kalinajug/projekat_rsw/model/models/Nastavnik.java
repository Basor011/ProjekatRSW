package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Nastavnik extends Korisnik {
    @OneToMany
    private List<ZadatakZaIzradu> zadaciZaIzradu;

    @OneToMany
    private List<Komentar> komentari;

    @OneToMany
    private List<Poeni> poeni;
}
