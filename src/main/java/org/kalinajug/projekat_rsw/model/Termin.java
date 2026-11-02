package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Entity
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime pocetak;
    private LocalDateTime kraj;

    @ManyToOne
    private Rok rok;

    @OneToMany(mappedBy = "termin")
    private List<Grupa> grupe;

    @OneToMany(mappedBy = "termin")
    private List<ListaPrijavljenih> listePrijavljenih;
}
