package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private IspitniRok ispitniRok;

    @OneToMany
    private List<Grupa> grupe;

    @OneToMany
    private List<ListaPrijavljenih> listePrijavljenih;
}
