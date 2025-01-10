package com.schoolmanagement.controller;


import com.schoolmanagement.dto.ClassDTO;
import com.schoolmanagement.entity.Class;
import com.schoolmanagement.exceptions.ClassAlreadyExistsException;
import com.schoolmanagement.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/classes")
@CrossOrigin(origins = "http://localhost:3000")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<Class> addClass(@RequestBody Class classEntity) throws ClassAlreadyExistsException {
        Class fetchedClass = classService.findClassByNameAndSection(classEntity.getName(),classEntity.getSection());
        if(fetchedClass != null) {
            throw new ClassAlreadyExistsException("Class already exists");
        }
        return ResponseEntity.ok(classService.addClass(classEntity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable Long id, @RequestBody Class updatedClass) {
        return ResponseEntity.ok(classService.updateClass(id, updatedClass));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClass(@PathVariable Long id) {
        Class fetchedClass = classService.findClass(id);
        if (fetchedClass != null) {
            classService.deleteClass(id);
        }else {
            return ResponseEntity.ok("Class not found");
        }
        return ResponseEntity.ok("Deleted");
    }

    @GetMapping("/allclasses")
    public ResponseEntity<List<ClassDTO>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }

    @ExceptionHandler(ClassAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleClassAlreadyExistsException(ClassAlreadyExistsException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT); // 409 Conflict
    }
}
