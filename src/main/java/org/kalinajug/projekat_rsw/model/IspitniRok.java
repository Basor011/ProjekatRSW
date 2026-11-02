package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class IspitniRok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naziv;

    private TipRoka tipRoka;

    @OneToMany(mappedBy = "ispitniRok")
    private List<Termin> termini;

    @ManyToOne(fetch = FetchType.LAZY)
    private Predmet predmet;

    public boolean isStudentPrijavljen(){
        return tipRoka == TipRoka.ISPIT;
    }
}
