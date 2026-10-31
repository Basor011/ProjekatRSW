package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int brojGrupe;

    @OneToMany(mappedBy = "zadatakZaIzradu")
    private List<ZadatakZaIzradu> zadaciZaIzradu;

    @ManyToOne
    private Termin termin;
}
