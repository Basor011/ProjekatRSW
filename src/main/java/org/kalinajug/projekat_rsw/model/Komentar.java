package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private String path;
    private Integer pocetniRed;
    private Integer krajnjiRed;
    private String tekst;           // u formatu pocetna linija, poslednja linija

    @ManyToOne(fetch = FetchType.LAZY)
    private PredatiZadatak predatiZadatak;
}
