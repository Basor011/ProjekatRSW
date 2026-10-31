package org.kalinajug.studsluzba_server.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Student extends Korisnik {
    private String brIndeksa;
    private String smer;
    private Integer godUpisa;
}
