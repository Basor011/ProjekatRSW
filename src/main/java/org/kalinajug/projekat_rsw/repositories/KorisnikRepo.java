package org.kalinajug.projekat_rsw.repositories;

import java.util.Optional;
import org.kalinajug.projekat_rsw.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepo extends JpaRepository<Korisnik, Long> {
    boolean existsByEmail(String email);
    Optional<Korisnik> findByEmailIgnoreCase(String email);
}
