package com.devlon.springsecurityamigos.student;

import com.devlon.springsecurityamigos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    private final UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "James Bond", ""),
            new Student(2L, "Olu Ade", ""),
            new Student(3L, "Afo Kunle", "")
    );

    @GetMapping("/")
    public String showIndex() {
        return "index";
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long studentId) {
        return STUDENTS.stream().filter(student -> student.getStudentId().equals(studentId))
                .findFirst().orElseThrow(()-> new IllegalStateException("Student " + studentId +" does not exist"));
    }

    @PostMapping("/")
    public Student createStudent(@RequestBody Student student) {
        return userService.createUser(student);
    }
}
