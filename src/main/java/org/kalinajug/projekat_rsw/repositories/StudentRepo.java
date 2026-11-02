package org.kalinajug.projekat_rsw.repositories;

import org.kalinajug.projekat_rsw.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {
}
