package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;

@Entity
public class Poeni {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PredatiZadatak predatiZadatak;

    @ManyToOne
    private Nastavnik nastavnik;
}
