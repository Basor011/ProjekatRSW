package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PredatiZadatakRepo extends JpaRepository<PredatiZadatak, Long> {
    boolean existsByZadatakZaIzraduId(Long id);
    Optional<PredatiZadatak> findByIdAndStudentId(Long id, Long studentId);
    List<PredatiZadatak>findByZadatakZaIzraduId(Long id);
}
