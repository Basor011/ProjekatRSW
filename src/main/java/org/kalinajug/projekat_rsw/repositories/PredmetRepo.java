package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredmetRepo extends JpaRepository<Predmet, Long> {
}
