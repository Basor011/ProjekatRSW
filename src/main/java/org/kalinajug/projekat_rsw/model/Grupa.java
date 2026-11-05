package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"termin_id", "broj_grupe"}))
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    private int brojGrupe;

    @OneToMany(mappedBy = "grupa")
    private List<ZadatakZaIzradu> zadaciZaIzradu;

    @ManyToOne(fetch = FetchType.LAZY)
    private Termin termin;
}
