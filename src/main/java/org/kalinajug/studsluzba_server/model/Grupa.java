package org.kalinajug.studsluzba_server.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Enabled;

@Entity
public class Grupa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
