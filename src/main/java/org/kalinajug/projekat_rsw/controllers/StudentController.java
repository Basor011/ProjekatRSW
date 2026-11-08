package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.StudentRequest;
import org.kalinajug.projekat_rsw.controllers.response.PageResponse;
import org.kalinajug.projekat_rsw.controllers.response.StudentResponse;
import org.kalinajug.projekat_rsw.model.Student;
import org.kalinajug.projekat_rsw.services.KorisnikService;
import org.kalinajug.projekat_rsw.services.StudentService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/student")
public class StudentController {
    private final StudentService studentService;
    private final KorisnikService korisnikService;

    @PostMapping(path="/add")
    public ResponseEntity<StudentResponse> addNewStudentPodaci(@RequestBody @Valid StudentRequest student) {
        Student student2= korisnikService.saveStudent(student.getIme(),student.getPrezime(),student.getEmail(),student.getLozinka(),student.getBrIndeksa(),student.getSmer(),student.getGodinaUpisa());
        StudentResponse studentResponse= Converters.toStudentResponse(student2);
        return ResponseEntity.status(HttpStatus.CREATED).body(studentResponse);
    }

    @GetMapping(path="/all")
    public List<StudentResponse> getAllStudentPodaci() {
        return Converters.toList(studentService.findAll(), Converters::toStudentResponse);
    }

    @GetMapping(path="/svi")
    public PageResponse<StudentResponse> getAllStudentPodaciPaginated(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return PageResponse.of(studentService.findAll(page, size), Converters::toStudentResponse);
    }

    @GetMapping(path="/podaci/{id}")
    public StudentResponse getStudentPodaci(@PathVariable Long id){
        return studentService.findById(id).map(Converters::toStudentResponse).orElse(null);
    }

}
