package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {



    @Query("select s from Student s where (:ime is null or lower(s.ime) like lower(concat('%', :ime, '%'))) " +
            "and (:prezime is null or lower(s.prezime) like lower(concat('%', :prezime, '%'))) " +
            "and s.brIndeksa is null")
    Page<Student> findStudent(@Param("ime") String ime, @Param("prezime") String prezime, Pageable pageable);
}

