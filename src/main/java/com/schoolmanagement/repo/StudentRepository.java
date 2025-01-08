package com.schoolmanagement.repo;


import com.schoolmanagement.dto.StudentDTO;
import com.schoolmanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStudentClassNameAndSection(String studentClassName, String section);


    Student findByRollNumber(String rollNumber);
}
