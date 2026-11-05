package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TerminRepo extends JpaRepository<Termin, Long> {
    List<Termin> findByRokId(Long rokId);
}
