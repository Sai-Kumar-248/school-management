package com.schoolmanagement.repo;


import com.schoolmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Find all students by class id
    List<Student> findByStudentClassName(String name);

    Student findByRollNumber(String rollNumber);
}
