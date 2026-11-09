package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

public interface PredatiZadatakRepo extends JpaRepository<PredatiZadatak, Long> {
    boolean existsByZadatakZaIzraduId(Long id);
    Optional<PredatiZadatak> findByIdAndStudentId(Long id, Long studentId);
    List<PredatiZadatak>findByZadatakZaIzraduId(Long id);
    Optional<PredatiZadatak> findByStudentIdAndZadatakZaIzraduId(Long id, Long studentId);

    long countByZadatakZaIzraduGrupaTerminId(Long terminId);
    long countByZadatakZaIzraduGrupaTerminIdAndVremePredajeNotNull(Long terminId);
    List<PredatiZadatak> findByZadatakZaIzraduGrupaTerminId(Long terminId);

    default Double prosecnoVremeIzrade(Long terminId) {
        return findByZadatakZaIzraduGrupaTerminId(terminId).stream().filter(p -> p.getVremePredaje() != null).mapToLong(p -> Duration.between(p.getVremePreuzimanja(), p.getVremePredaje()).toMinutes()).average().orElse(0.0);
    }

    @Query("""
    select p from PredatiZadatak p
      join fetch p.student s
      join fetch p.zadatakZaIzradu z
      join fetch z.grupa g
    where g.termin.id = :terminId
      and (:brojGrupe is null or g.brojGrupe = :brojGrupe)
      and (:samoPredata = false or p.vremePredaje is not null)
      and (:samoNeocenjena = false or p.poeni is null)
    order by g.brojGrupe, s.prezime, s.ime
    """)
    List<PredatiZadatak> pronadjiPoKriterijumima(@Param("terminId") Long terminId,
                                                 @Param("brojGrupe") Integer brojGrupe,
                                                 @Param("samoPredata") boolean samoPredata,
                                                 @Param("samoNeocenjena") boolean samoNeocenjena);
}
