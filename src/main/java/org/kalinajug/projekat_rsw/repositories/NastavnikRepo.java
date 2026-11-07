package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NastavnikRepo extends JpaRepository<Nastavnik, Long> {


    @Query("SELECT DISTINCT n FROM Nastavnik n LEFT JOIN FETCH n.zadaciZaIzradu WHERE n.id = :id")
    Optional<Nastavnik> findByIdWithZadaci(@Param("id") Long id);


    @Query("SELECT DISTINCT n FROM Nastavnik n LEFT JOIN FETCH n.zadaciZaIzradu")
    List<Nastavnik> findAllWithZadaci();


    @Query("SELECT DISTINCT sp FROM Nastavnik sp LEFT JOIN FETCH sp.zadaciZaIzradu WHERE "
            + "(:ime is null or lower(sp.ime) like lower(concat('%', :ime, '%'))) and "
            + "(:prezime is null or lower(sp.prezime) like lower(concat('%', :prezime, '%')))")
    List<Nastavnik> findByImeAndPrezime(@Param("ime") String ime, @Param("prezime") String prezime);

    List<Nastavnik> findByEmailIn(List<String> emails);
}