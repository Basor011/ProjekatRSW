package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRepo extends JpaRepository<Grupa, Long> {

  @Query("select g.zadaciZaIzradu from Grupa g where g.id= :grupaId")
  List<ZadatakZaIzradu> getZadaciOdGrupe(@Param("grupaId") Long grupaId);

    @Query("SELECT DISTINCT g FROM Grupa g LEFT JOIN FETCH g.zadaciZaIzradu WHERE g.termin.id = :terminId")
    List<Grupa> findByTerminId(@Param("terminId")Long terminId);

  boolean existsByTerminIdAndBrojGrupe(Long terminId, int brojGrupe);
}
