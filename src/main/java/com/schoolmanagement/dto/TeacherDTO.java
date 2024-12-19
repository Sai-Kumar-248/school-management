package com.schoolmanagement.dto;

import com.schoolmanagement.entity.Subject;
import com.schoolmanagement.entity.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherDTO {

    private Long id;

    private String name;

    private String email;

    private String phone;



    private List<SubjectDTO> subjects = new ArrayList<>();

    public TeacherDTO(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.email = teacher.getEmail();
        this.phone = teacher.getPhone();
        // Convert Subject Entities to SubjectDTOs
        this.subjects = teacher.getSubjects()
                .stream()
                .map(SubjectDTO::new)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }


}
