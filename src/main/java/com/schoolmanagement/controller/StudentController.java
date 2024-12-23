package com.schoolmanagement.controller;

import com.schoolmanagement.dto.StudentDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;


import com.schoolmanagement.entity.Student;
import com.schoolmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
   // @PreAuthorize("hasRole('USER')")
    public List<StudentDTO> getAllStudents() {

        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/addStudent")
   // @PreAuthorize("hasRole('USER')")
    public Student createStudent(@RequestBody Student student) {

        return studentService.saveStudent(student);
    }

    @PutMapping("/updateStudent/{id}")
    public Optional<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Student> updatedStudent=    studentService.updateStudentById(id,student);

        return updatedStudent;

    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (studentService.getStudentById(id).isPresent()) {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/class/{className}")
    public ResponseEntity<List<StudentDTO>> getStudentsByClass(@RequestParam(value = "The name of the class", required = true) @PathVariable String name) {
        List<StudentDTO> students = studentService.getStudentsByClass(name);
        return ResponseEntity.ok(students);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateStudentPartial(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Student updatedStudent = studentService.updateStudentPartial(id, updates);
        return ResponseEntity.ok(updatedStudent);
    }
}

