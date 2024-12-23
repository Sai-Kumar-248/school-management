package com.schoolmanagement.controller;


import com.schoolmanagement.dto.ClassDTO;
import com.schoolmanagement.entity.Class;
import com.schoolmanagement.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
@CrossOrigin
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<Class> addClass(@RequestBody Class classEntity) {
        return ResponseEntity.ok(classService.addClass(classEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable Long id, @RequestBody Class updatedClass) {
        return ResponseEntity.ok(classService.updateClass(id, updatedClass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        classService.deleteClass(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }
}
