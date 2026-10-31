package org.kalinajug.projekat_rsw.model.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public abstract class Korisnik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ime;
    private String prezime;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String lozinka;
}
