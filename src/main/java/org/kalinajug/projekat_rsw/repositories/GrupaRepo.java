package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.model.Termin;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GrupaRepo extends JpaRepository<Grupa, Long> {

  @Query("select g.zadaciZaIzradu from Grupa g where g.id= :grupaId")
  List<ZadatakZaIzradu> getZadaciOdGrupe(@Param("grupaId") Long grupaId);

  List<Grupa> findByTerminId(Long terminId);

  boolean existsByTerminIdAndBrojGrupe(Long terminId, int brojGrupe);
}
