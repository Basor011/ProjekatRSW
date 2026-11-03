package org.kalinajug.projekat_rsw.model;

import ch.qos.logback.core.joran.sanity.Pair;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class Komentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long komentarid;

    @Column(nullable = false)
    private String path;
    private Integer pocetniRed;
    private Integer krajnjiRed;
    private String komentar;           // u formatu pocetna linija, poslednja linija

    @ManyToOne(fetch = FetchType.LAZY)
    private PredatiZadatak predatiZadatak;
}
