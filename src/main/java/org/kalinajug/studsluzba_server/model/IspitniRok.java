package org.kalinajug.studsluzba_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IspitniRok {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
