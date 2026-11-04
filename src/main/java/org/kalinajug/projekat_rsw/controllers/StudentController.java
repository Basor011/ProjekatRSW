package org.kalinajug.projekat_rsw.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kalinajug.projekat_rsw.controllers.request.StudentRequest;
import org.kalinajug.projekat_rsw.controllers.response.StudentResponse;
import org.kalinajug.projekat_rsw.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/api/student")
public class StudentController {

    private final StudentService studentService;


}
