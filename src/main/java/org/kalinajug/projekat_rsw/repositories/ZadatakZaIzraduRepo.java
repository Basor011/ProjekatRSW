package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZadatakZaIzraduRepo extends JpaRepository<ZadatakZaIzradu, Long> {
    boolean existsByGrupaId(Long grupaId);
    Optional<ZadatakZaIzradu> findByGrupaTerminIdAndGrupaBrojGrupe(Long terminId, Integer brojGrupe);
}
