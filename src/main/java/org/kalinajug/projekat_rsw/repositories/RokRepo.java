package org.kalinajug.projekat_rsw.repositories;


import org.kalinajug.projekat_rsw.model.Rok;
import org.kalinajug.projekat_rsw.model.TipRoka;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RokRepo extends JpaRepository<Rok, Long> {

    @Query("select r from Rok r where r.tipRoka = :tipRoka")
    List<Rok> getRokByTipRoka(@Param("tipRoka") TipRoka tipRoka);


}
