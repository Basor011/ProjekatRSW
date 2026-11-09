package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("select s from Student s where lower(s.email) in :emails")
    List<Student> findByEmailInIgnoreCase(@Param("emails") List<String> emails);


    @Query("select s from Student s where s.id = :studentId")
    Optional<Student> findByIdOptional(@Param("studentId") Long studentId);
}
