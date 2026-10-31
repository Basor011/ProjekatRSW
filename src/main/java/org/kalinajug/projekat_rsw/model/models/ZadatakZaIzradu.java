package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ZadatakZaIzradu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Grupa grupa;

    @ManyToOne
    private Nastavnik nastavnik;

    @OneToMany
    private List<PredatiZadatak> predatiZadaci;
}
