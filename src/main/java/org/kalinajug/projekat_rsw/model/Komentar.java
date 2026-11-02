package org.kalinajug.projekat_rsw.model;

import ch.qos.logback.core.joran.sanity.Pair;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path; // put do klase za koju ostavlja komtenar
    private Pair<Integer,Integer> row; // za koju liniju ili linije ostavlja komentar,
    private String komentar;           // u formatu pocetna linija, poslednja linija

    @ManyToOne(fetch = FetchType.LAZY)
    private PredatiZadatak predatiZadatak;
}
