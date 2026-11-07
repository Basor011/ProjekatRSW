package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Nastavnik;
import org.kalinajug.projekat_rsw.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findByEmailIn(List<String> emails);
    @Query("select s from Student s where s.id= :studentId")
    List<Student> getStudentIByIdById(@Param("nastavnikId")Long studentId);
}
