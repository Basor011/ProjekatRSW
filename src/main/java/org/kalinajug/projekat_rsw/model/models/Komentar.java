package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;


@Entity
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PredatiZadatak predatiZadatak;

    @ManyToOne
    private Nastavnik nastavnik;
}
