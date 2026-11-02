package org.kalinajug.projekat_rsw.model;

import jakarta.persistence.*;
import lombok.Data;

@Inheritance(strategy = InheritanceType.JOINED)
@Data
@Entity
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
