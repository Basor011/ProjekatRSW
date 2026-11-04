package org.kalinajug.projekat_rsw.repositories;


import org.kalinajug.projekat_rsw.model.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KomentarRepo extends JpaRepository<Komentar, Long> {


}
