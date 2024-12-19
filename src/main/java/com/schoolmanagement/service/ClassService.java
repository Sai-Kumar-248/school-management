package com.schoolmanagement.service;


import com.schoolmanagement.dto.ClassDTO;
import com.schoolmanagement.entity.Class;
import com.schoolmanagement.repo.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public Class addClass(Class classEntity) {
        return classRepository.save(classEntity);
    }

    public Class updateClass(Long id, Class updatedClass) {
        Class existingClass = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found with ID: " + id));
        existingClass.setName(updatedClass.getName());
        existingClass.setSection(updatedClass.getSection());
        //existingClass.setStudents(updatedClass.getStudents());
        //existingClass.setTeacher(updatedClass.getTeacher());
        return classRepository.save(existingClass);
    }

    public void deleteClass(Long id) {
        if (!classRepository.existsById(id)) {
            throw new RuntimeException("Class not found with ID: " + id);
        }
        classRepository.deleteById(id);
    }

    public List<ClassDTO> getAllClasses() {
        List<Class> allClasses = classRepository.findAll();

        return allClasses.stream().map(ClassDTO::new).collect(Collectors.toList());
    }
}
