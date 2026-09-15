package com.department.departmentmanagement.controller;

import com.department.departmentmanagement.dto.LoginRequest;
import com.department.departmentmanagement.entity.Staff;
import com.department.departmentmanagement.repository.StaffRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
public class LoginController {

    private final StaffRepository staffRepository;

    public LoginController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        Optional<Staff> staffOptional =
                staffRepository.findByUsername(loginRequest.getUsername());

        if (staffOptional.isEmpty()) {
            return ResponseEntity
                    .status(401)
                    .body("Invalid username or password");
        }

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
}