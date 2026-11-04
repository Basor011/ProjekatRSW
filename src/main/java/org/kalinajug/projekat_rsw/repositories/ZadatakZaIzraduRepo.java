package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.ZadatakZaIzradu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ZadatakZaIzraduRepo extends JpaRepository<ZadatakZaIzradu, Long> {
}
