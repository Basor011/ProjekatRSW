package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime pocetak;
    @Column(nullable = false)
    private LocalDateTime kraj;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rok rok;

    @OneToMany(mappedBy = "termin")
    private Set<Grupa> grupe;

    @OneToMany(mappedBy = "termin")
    private List<ListaPrijavljenih> listePrijavljenih;
}
