package org.kalinajug.projekat_rsw.services;


import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.model.Student;
import org.kalinajug.projekat_rsw.repositories.StudentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

   final StudentRepo studentRepo;

    public Student save(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    public Page<Student> findAll(int page, int size) {
        return studentRepo.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    public Optional<Student> findById(Long id) {
        return studentRepo.findById(id);
    }

    public Page<Student> searchBezIndeksa(String ime, String prezime, int page, int size) {
        return studentRepo.findStudent(ime, prezime, PageRequest.of(page, size, Sort.by("id").descending()));
    }

}
