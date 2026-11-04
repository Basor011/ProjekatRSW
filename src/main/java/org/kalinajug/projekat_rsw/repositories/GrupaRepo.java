package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Grupa;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRepo extends JpaRepository<Grupa, Long> {

  @Query("select g.zadaciZaIzradu from Grupa g where g.groupid= :grupaId")
  List<ZadatakZaIzradu> getZadaciOdGrupe(@Param("grupaId") Long grupaId);



}
