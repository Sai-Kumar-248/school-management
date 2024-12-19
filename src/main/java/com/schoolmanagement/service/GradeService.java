package com.schoolmanagement.service;


import com.schoolmanagement.entity.Grade;
import com.schoolmanagement.repo.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    // Add a new grade
    public Grade addGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    // Update an existing grade
    public Grade updateGrade(Long id, Grade gradeDetails) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
        existingGrade.setSubject(gradeDetails.getSubject());
        existingGrade.setGrade(gradeDetails.getGrade());
//        existingGrade.setStudent(gradeDetails.getStudent());
//        existingGrade.setTeacher(gradeDetails.getTeacher());
//        existingGrade.setClassEntity(gradeDetails.getClassEntity());
        return gradeRepository.save(existingGrade);
    }

    // Delete a grade
    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    // Get all grades
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    // Get grade by ID
    public Grade getGradeById(Long id) {
        return gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found with id: " + id));
    }
}
