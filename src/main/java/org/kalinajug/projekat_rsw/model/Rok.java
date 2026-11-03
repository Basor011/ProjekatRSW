package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class Rok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long rokid;

    @Column(nullable = false)
    private String naziv;

    @Enumerated(EnumType.STRING)
    private TipRoka tipRoka;

    @OneToMany(mappedBy = "rok")
    private List<Termin> termini;

    @ManyToOne(fetch = FetchType.LAZY)
    private Predmet predmet;

    public boolean isStudentPrijavljen(){
        return tipRoka == TipRoka.ISPIT;
    }
}
