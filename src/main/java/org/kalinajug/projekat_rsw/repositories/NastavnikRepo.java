package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NastavnikRepo extends JpaRepository<Nastavnik, Long> {

    @Query("select n from Nastavnik n where n.id= :nastavnikId")
    List<Nastavnik> getNastavnikById(@Param("nastavnikId")Long nastavnikId);

    @Query("select n.zadaciZaIzradu from Nastavnik n where n.id =:nastavnikId")
    List<ZadatakZaIzradu> getNastavnikZadaciZaIzradu(@Param("nastavnikId")Long nastavnikId);

}
