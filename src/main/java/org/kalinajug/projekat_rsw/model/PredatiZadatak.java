package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "zadatak_za_izradu_id"}))
public class PredatiZadatak {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(mappedBy = "predatiZadatak")
    private List<Komentar> komentari;

    private Double poeni;

    private LocalDateTime vremePreuzimanja;
    private LocalDateTime vremePredaje; //null dok student ne preda
    //vremeIzrade = vremePredaje - vremePreuzimanja

    private String resenjePath;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    private ZadatakZaIzradu zadatakZaIzradu;


}
