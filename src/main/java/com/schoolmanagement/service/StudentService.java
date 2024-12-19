package com.schoolmanagement.service;

import com.schoolmanagement.dto.StudentDTO;
import com.schoolmanagement.repo.ClassRepository;
import com.schoolmanagement.repo.StudentRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import com.schoolmanagement.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ClassRepository classRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<StudentDTO> getAllStudents() {
        List<Student> allStudents=     studentRepository.findAll();

        return allStudents.stream().map(StudentDTO::new).collect(Collectors.toList());
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student saveStudent(Student student) {

        Student oldStudent = studentRepository.findByRollNumber(student.getRollNumber());

        if(oldStudent != null) {
            throw new RuntimeException("Student already exists");
        }


        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public Optional<Student> updateStudentById(Long id, Student student) {

        Student oldstudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));


       // oldstudent.setId(student.getId()); // Use the new ID (the one you want)
        oldstudent.setRollNumber(student.getRollNumber());
        oldstudent.setName(student.getName());
        oldstudent.setAge(student.getAge());
        oldstudent.setSection(student.getSection());
        //oldstudent.setClassEntity(student.getClassEntity());
        oldstudent.setStudentClassName(student.getStudentClassName());

        // Save the new student
        studentRepository.save(oldstudent);

        // Return the new student as an optional
        return Optional.of(oldstudent);
    }

    public List<StudentDTO> getStudentsByClass(String name){
        // Fetching students for the given class ID
        List<Student> byStudentClassName = studentRepository.findByStudentClassName(name);
        return byStudentClassName.stream().map(StudentDTO::new).collect(Collectors.toList());

    }

    @Transactional
    public Student updateStudentPartial(Long id, Map<String, Object> updates) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Manually update fields based on the map keys
        if (updates.containsKey("rollNumber")) {
            student.setName((String) updates.get("rollNumber"));
        }
        if (updates.containsKey("name")) {
            student.setName((String) updates.get("name"));
        }

        if (updates.containsKey("grade")) {
            student.setSection((String) updates.get("grade"));
        }

        if (updates.containsKey("age")) {
            student.setAge((Integer) updates.get("age"));
        }
        if (updates.containsKey("studentClassName")) {
            student.setAge((Integer) updates.get("studentClassName"));
        }

        return studentRepository.save(student);
    }
}
