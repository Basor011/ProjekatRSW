package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NastavnikRepo extends JpaRepository<Nastavnik, Long> {

    @Query("select n from Nastavnik n where n.korisnikid= :nastavnikId")
    List<Nastavnik> getNastavnikById(@Param("nastavnikId")Long nastavnikId);

    @Query("select n.zadaciZaIzradu from Nastavnik n where n.korisnikid =:nastavnikId")
    List<ZadatakZaIzradu> getNastavnikZadaciZaIzradu(@Param("nastavnikId")Long nastavnikId);

    @Query("select sp from Nastavnik sp where "
            + "(:ime is null or lower(sp.ime) like lower(concat('%', :ime, '%'))) and "
            + "(:prezime is null or lower(sp.prezime) like lower(concat('%', :prezime, '%')))")
    List<Nastavnik> findByImeAndPrezime(String ime, String prezime);

    List<Nastavnik> findByEmailIn(List<String> emails);

}
