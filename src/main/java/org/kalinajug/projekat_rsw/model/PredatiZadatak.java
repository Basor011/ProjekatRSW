package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class PredatiZadatak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "predatiZadatak")
    private List<Komentar> komentari;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @OneToOne(mappedBy = "predatiZadatak")
    private Poeni poeni;

    @ManyToOne(fetch = FetchType.LAZY)
    private ZadatakZaIzradu zadatakZaIzradu;

}
