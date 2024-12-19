package com.schoolmanagement.entity;


import jakarta.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;  // Subject for which the grade is assigned
    private String grade;    // Grade (e.g., A, B, C, etc.)

//    @ManyToOne
//    @JoinColumn(name = "student_id", nullable = false)
//    private Student student;  // The student who receives the grade
//
//    @ManyToOne
//    @JoinColumn(name = "teacher_id", nullable = false)
//    private Teacher teacher;  // The teacher who assigns the grade
//
//    @ManyToOne
//    @JoinColumn(name = "class_id", nullable = false)
//    private Class classEntity;  // The class to which the student belongs

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

//    public Student getStudent() {
//        return student;
//    }
//
//    public void setStudent(Student student) {
//        this.student = student;
//    }
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
//
//    public Class getClassEntity() {
//        return classEntity;
//    }
//
//    public void setClassEntity(Class classEntity) {
//        this.classEntity = classEntity;
//    }
}
