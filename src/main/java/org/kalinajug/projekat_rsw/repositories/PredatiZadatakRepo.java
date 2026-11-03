package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.PredatiZadatak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredatiZadatakRepo extends JpaRepository<PredatiZadatak, Long> {


}
