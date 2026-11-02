package org.kalinajug.projekat_rsw.model;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private PredatiZadatak predatiZadatak;

    @ManyToOne(fetch = FetchType.LAZY)
    private Nastavnik nastavnik;
}
