package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query("select sp from StudentPodaci sp where "
            + "(:ime is null or lower(sp.ime) like lower(concat('%', :ime, '%'))) and "
            + "(:prezime is null or lower(sp.prezime) like lower(concat('%', :prezime, '%'))) and "
            + "not exists (select indeks from StudentIndeks indeks where indeks.student = sp)")
    Page<Student> findStudent(String ime, String prezime, Pageable pageable);
}
