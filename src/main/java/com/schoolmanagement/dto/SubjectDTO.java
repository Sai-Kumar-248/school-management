package com.schoolmanagement.dto;

import com.schoolmanagement.entity.Subject;

public class SubjectDTO {
    private Long subjectId;
    private String subjectName;

    // Constructor to convert Entity to DTO
    public SubjectDTO(Subject subject) {
        this.subjectId = subject.getSubjectId();
        this.subjectName = subject.getSubjectName();
    }

    // Getters and Setters
    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
