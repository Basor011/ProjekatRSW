package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime pocetak;
    private LocalDateTime kraj;

    @ManyToOne
    private IspitniRok ispitniRok;

    @OneToMany(mappedBy = "termin")
    private List<Grupa> grupe;

    @OneToMany(mappedBy = "termin")
    private List<ListaPrijavljenih> listePrijavljenih;
}
