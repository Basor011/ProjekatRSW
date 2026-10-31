package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;


@Entity
public class ListaPrijavljenih {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "termin_id")
    private Termin termin;

}
