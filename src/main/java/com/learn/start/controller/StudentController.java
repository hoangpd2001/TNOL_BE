package com.learn.start.controller;

import com.learn.start.dto.request.StudentCreationRequest;

import com.learn.start.entity.Student;
import com.learn.start.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping
    Student createUser(@RequestBody StudentCreationRequest request) {
        return studentService.creatStudent(request);
    }
    @GetMapping
    List<Student> selectUser() {
        return studentService.selectStudent();
    }
    @GetMapping("/{studentId}")
    Student selectUser(@PathVariable("studentId") String studentId) {
        return studentService.selectStudentById(studentId);
    }


}
