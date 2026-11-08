package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Predmet;
import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TerminRepo extends JpaRepository<Termin, Long> {
    List<Termin> findByRokId(Long rokId);


    @Query("SELECT DISTINCT t FROM Termin t LEFT JOIN FETCH t.listePrijavljenih LEFT JOIN FETCH t.grupe")
    List<Termin> findAllWithListaPrijavljenih();

}
