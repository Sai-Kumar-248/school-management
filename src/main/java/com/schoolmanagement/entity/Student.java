package com.schoolmanagement.entity;



import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rollNumber;
    private String name;
    private int age;
    private String section;
    private String studentClassName;




//    @ManyToOne
//    @JoinColumn(name = "class_id")
//    private Class classEntity;


//
//    public Class getClassEntity() {
//        return classEntity;
//    }
//
//    public void setClassEntity(Class classEntity) {
//        this.classEntity = classEntity;
//    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getStudentClassName() {
        return studentClassName;
    }

    public void setStudentClassName(String studentClassName) {
        this.studentClassName = studentClassName;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String grade) {
        this.section = grade;
    }




}
