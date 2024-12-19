package com.schoolmanagement.dto;

import com.schoolmanagement.entity.Student;

public class StudentDTO {

    private Long id;
    private String rollNumber;
    private String name;
    private int age;
    private String grade;
    private String studentClassName;

    public StudentDTO(Student student) {
        this.id = student.getId();
        this.rollNumber = student.getRollNumber();
        this.name = student.getName();
        this.age = student.getAge();
        this.grade = student.getSection();
        this.studentClassName = student.getStudentClassName();
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName = studentClassName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

}
