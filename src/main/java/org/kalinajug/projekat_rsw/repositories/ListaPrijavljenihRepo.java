package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.ListaPrijavljenih;
import org.kalinajug.projekat_rsw.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ListaPrijavljenihRepo extends JpaRepository<ListaPrijavljenih, Long> {


}
