package com.department.departmentmanagement.controller;

import com.department.departmentmanagement.dto.LoginRequest;
import com.department.departmentmanagement.entity.Staff;
import com.department.departmentmanagement.entity.Student;
import com.department.departmentmanagement.repository.StaffRepository;
import com.department.departmentmanagement.repository.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class LoginController {

    private final StaffRepository staffRepository;
    private final StudentRepository studentRepository;

    public LoginController(
            StaffRepository staffRepository,
            StudentRepository studentRepository) {

        this.staffRepository = staffRepository;
        this.studentRepository = studentRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        // Check Staff/Admin login first
        Optional<Staff> staffOptional =
                staffRepository.findByUsername(loginRequest.getUsername());

        if (staffOptional.isPresent()) {

            Staff staff = staffOptional.get();

            if (!staff.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity
                        .status(401)
                        .body("Invalid username or password");
            }

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Login successful");
            response.put("staffId", staff.getStaffId());
            response.put("staffName", staff.getStaffName());
            response.put("username", staff.getUsername());
            response.put("role", staff.getRole());

            return ResponseEntity.ok(response);
        }

        // Check Student login
        Optional<Student> studentOptional =
                studentRepository.findByRegisterNumber(
                        loginRequest.getUsername());

        if (studentOptional.isPresent()) {

            Student student = studentOptional.get();

            if (!student.getPassword().equals(loginRequest.getPassword())) {
                return ResponseEntity
                        .status(401)
                        .body("Invalid username or password");
            }

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Login successful");
            response.put("studentId", student.getStudentId());
            response.put("studentName", student.getName());
            response.put("username", student.getRegisterNumber());
            response.put("role", "STUDENT");

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(401)
                .body("Invalid username or password");
    }
}