package org.kalinajug.projekat_rsw.repositories;


import org.kalinajug.projekat_rsw.model.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KomentarRepo extends JpaRepository<Komentar, Long> {
    List<Komentar> findByPredatiZadatakId(Long predatiZadatakId);
}
