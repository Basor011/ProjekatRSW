package org.kalinajug.projekat_rsw.services;


import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.exceptions.ResourceNotFoundException;
import org.kalinajug.projekat_rsw.model.Student;
import org.kalinajug.projekat_rsw.repositories.StudentRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public Student findById(Long id) {
        return studentRepo.findByIdOptional(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ne postoji student sa id: " + id));
    }



}
