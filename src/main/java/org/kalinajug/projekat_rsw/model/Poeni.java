package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
