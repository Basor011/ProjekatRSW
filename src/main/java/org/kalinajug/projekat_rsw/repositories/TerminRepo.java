package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TerminRepo extends JpaRepository<Termin, Long> {
}
