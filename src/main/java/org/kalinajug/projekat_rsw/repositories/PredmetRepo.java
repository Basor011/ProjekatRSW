package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.Predmet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredmetRepo extends JpaRepository<Predmet, Long> {

    @Query("SELECT DISTINCT p FROM Predmet p")
    List<Predmet> findAll();

    @Query("SELECT DISTINCT p FROM Predmet p LEFT JOIN FETCH p.rokovi")
    List<Predmet> findAllWithRokovi();
}
