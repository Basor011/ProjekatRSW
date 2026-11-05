package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.StudentRequest;
import org.kalinajug.projekat_rsw.controllers.response.PageResponse;
import org.kalinajug.projekat_rsw.controllers.response.StudentResponse;
import org.kalinajug.projekat_rsw.services.StudentService;
import org.kalinajug.projekat_rsw.utils.Converters;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/student")
public class StudentController {
    private final StudentService studentService;

    @PostMapping(path="/add")
    public Long addNewStudentPodaci(@RequestBody @Valid StudentRequest student) {
        return studentService.save(Converters.toStudent(student)).getId();
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
