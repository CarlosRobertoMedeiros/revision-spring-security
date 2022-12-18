package br.com.roberto.springsecurity.rest;

import br.com.roberto.springsecurity.domain.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentRest {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Carlos Roberto"),
            new Student(2, "Luciene Alves"),
            new Student(3, "Yan Victor"),
            new Student(4, "Matheus Alves"),
            new Student(5, "Vincius Alves")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENTS.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId+ " does not exists"));
    }



}
