package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Entity
public class ZadatakZaIzradu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long zadatakzaIzraduid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Grupa grupa;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Nastavnik nastavnik;

    @OneToMany(mappedBy = "zadatakZaIzradu")
    private List<PredatiZadatak> predatiZadaci;
}
