package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class PredatiZadatak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Komentar> komentari;

    @ManyToOne
    private Student student;

    @OneToOne
    private Poeni poeni;

    @ManyToOne
    private ZadatakZaIzradu zadatakZaIzradu;

}
