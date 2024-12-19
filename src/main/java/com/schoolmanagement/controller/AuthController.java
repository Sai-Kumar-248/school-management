package com.schoolmanagement.controller;


import com.schoolmanagement.dto.JwtResponse;
import com.schoolmanagement.dto.ValidUser;
import com.schoolmanagement.entity.LoginRequest;
import com.schoolmanagement.entity.User;
import com.schoolmanagement.repo.UserRepository;
import com.schoolmanagement.service.AuthService;
import com.schoolmanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Hardcoded credentials for simplicity
        if ("user".equals(loginRequest.getUsername()) && "password".equals(loginRequest.getPassword())) {
            String token = jwtUtil.generateToken(loginRequest.getUsername(), Arrays.asList("USER"));
            return ResponseEntity.ok(new JwtResponse(token));
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
//    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//        // Hardcoded credentials for simplicity
//
//
//        User user = userRepository.findByUsername(loginRequest.getUsername());
//        if (user.getUsername().equals(loginRequest.getUsername()) && user.getPassword().equals(loginRequest.getPassword())) {
//            String token = jwtUtil.generateToken(loginRequest.getUsername(), Arrays.asList("USER"));
//            return ResponseEntity.ok(new JwtResponse(token));
//        }
//        return ResponseEntity.status(401).body("Invalid credentials");
//    }

}

//
//@RestController
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//    @Autowired
//    private AuthService authService;
//
//    @PostMapping("/login")
//    public ValidUser login(@RequestBody LoginRequest loginRequest) {
//        boolean isValidUser  = authService.validateUser (loginRequest.getUsername(), loginRequest.getPassword());
//        if (isValidUser ) {
//
//            ValidUser validUser = new ValidUser();
//            validUser.setMessage("Success");
//            validUser.setValidUser(true);
//
//            return validUser;
//        } else {
//            ValidUser validUser = new ValidUser();
//            validUser.setMessage("Failure");
//            validUser.setValidUser(false);
//            return validUser;
//        }
//    }
//}
