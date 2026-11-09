package org.kalinajug.projekat_rsw.repositories;


import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.model.TipRoka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RokRepo extends JpaRepository<Rok, Long> {

    @Query("select r from Rok r where r.tipRoka = :tipRoka")
    List<Rok> getRokByTipRoka(@Param("tipRoka") TipRoka tipRoka);

    @Query("SELECT DISTINCT r FROM Rok r LEFT JOIN FETCH r.termini WHERE r.predmet.id = :predmetId")
    List<Rok> findByPredmetIdWithTermini(@Param("predmetId") Long predmetId);

    @Query("SELECT DISTINCT r FROM Rok r LEFT JOIN FETCH r.termini WHERE r.id = :id")
    List<Rok> findByRokIdWithTermini(@Param ("id") Long id);
   ;

}
