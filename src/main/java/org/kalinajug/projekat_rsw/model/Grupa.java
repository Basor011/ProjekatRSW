package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int brojGrupe;

    @OneToMany(mappedBy = "grupa")
    private List<ZadatakZaIzradu> zadaciZaIzradu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Termin termin;
}
