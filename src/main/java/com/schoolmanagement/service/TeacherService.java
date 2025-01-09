package com.schoolmanagement.service;


import com.schoolmanagement.dto.SubjectDTO;
import com.schoolmanagement.dto.TeacherDTO;
import com.schoolmanagement.entity.Subject;
import com.schoolmanagement.entity.Teacher;
import com.schoolmanagement.repo.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDTO(savedTeacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherId(teacher.getTeacherId());
        dto.setTeacherName(teacher.getTeacherName());
        dto.setTeacherPhone(teacher.getTeacherPhone());
        dto.setTeacherEmail(teacher.getTeacherEmail());

        // Convert List<Subject> to List<SubjectDTO>
        List<SubjectDTO> subjectDTOs = teacher.getSubjects().stream()
                .map(subject -> {
                    SubjectDTO subjectDTO = new SubjectDTO();
                    subjectDTO.setId(subject.getId());
                    subjectDTO.setName(subject.getName());
                    return subjectDTO;
                })
                .collect(Collectors.toList());

        dto.setSubjects(subjectDTOs);
        return dto;
    }
    private Teacher convertToEntity(TeacherDTO teacherDTO) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherDTO.getTeacherId());
        teacher.setTeacherName(teacherDTO.getTeacherName());
        teacher.setTeacherPhone(teacherDTO.getTeacherPhone());
        teacher.setTeacherEmail(teacherDTO.getTeacherEmail());

        // Convert List<SubjectDTO> to List<Subject>
        List<Subject> subjects = teacherDTO.getSubjects().stream()
                .map(subjectDTO -> {
                    Subject subject = new Subject();
                    subject.setId(subjectDTO.getId()); // Assuming you want to set the ID if it exists
                    subject.setName(subjectDTO.getName());
                    return subject;
                })
                .collect(Collectors.toList());

        teacher.setSubjects(subjects);
        return teacher;
    }

    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        return teacherRepository.findById(id).map(existingTeacher -> {
            existingTeacher.setTeacherName(updatedTeacher.getTeacherName());
            existingTeacher.setTeacherPhone(updatedTeacher.getTeacherPhone());
            existingTeacher.setTeacherEmail(updatedTeacher.getTeacherEmail());
            existingTeacher.setSubjects(updatedTeacher.getSubjects());
            return teacherRepository.save(existingTeacher);
        }).orElse(null);
    }
}