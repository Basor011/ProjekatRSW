package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Predmet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<IspitniRok> ispitniRokovi;
}
