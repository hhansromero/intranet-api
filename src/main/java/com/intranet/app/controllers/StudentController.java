package com.intranet.app.controllers;

import com.intranet.app.db.models.Student;
import com.intranet.app.models.StudentDTO;
import com.intranet.app.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/students")
    public Flux<Student> findAllStudents() {
        return studentService.findAllStudents();
    }

    @GetMapping("/students/{id}")
    public Mono<Student> findStudentById(@PathVariable(value = "id") Long id) {
        try {
            return studentService.findStudentById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Any students with the id %s.", id), e);
        }
    }

    @GetMapping("/students-full/{id}")
    public Mono<StudentDTO> findStudentDTOById(@PathVariable(value = "id") Long id) {
        try {
            return studentService.findStudentDTOById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Any students with the id %s.", id), e);
        }
    }
}
