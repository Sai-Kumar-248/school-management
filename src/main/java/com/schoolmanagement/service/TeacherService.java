package com.schoolmanagement.service;


import com.schoolmanagement.dto.TeacherDTO;
import com.schoolmanagement.entity.Subject;
import com.schoolmanagement.entity.Teacher;
import com.schoolmanagement.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    // Add a new teacher
    public TeacherDTO addTeacher(Teacher teacher) {

        Teacher oldTeacher =teacherRepository.findByPhone(teacher.getPhone());

        if(oldTeacher != null){
            throw new RuntimeException("Teacher Already Exists");
        }

        // Set the teacher reference in each subject
        for (Subject subject : teacher.getSubjects()) {
            subject.setSubjectId(teacher.getId());
            subject.setTeacher(teacher);
        }
        teacherRepository.save(teacher);


        return new TeacherDTO(teacher);
    }

    // Update teacher information
    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        existingTeacher.setName(teacherDetails.getName());
        existingTeacher.setEmail(teacherDetails.getEmail());
        existingTeacher.setPhone(teacherDetails.getPhone());
        return teacherRepository.save(existingTeacher);
    }

    // Delete a teacher
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    // Get all teachers
    public List<TeacherDTO> getAllTeachers() {
        List<Teacher> allTeachers=   teacherRepository.findAll();

        return allTeachers.stream().map(TeacherDTO::new).collect(Collectors.toList());
    }

    // Get teacher by id
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
    }
}
