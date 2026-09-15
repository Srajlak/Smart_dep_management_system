package com.department.departmentmanagement.controller;

import com.department.departmentmanagement.entity.Staff;
import com.department.departmentmanagement.service.StaffService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
@RestController
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Add staff
    @PostMapping("/staff")
    public Staff saveStaff(@RequestBody Staff staff) {
        return staffService.saveStaff(staff);
    }

    // Get all staff
    @GetMapping("/staff")
    public List<Staff> fetchStaffList() {
        return staffService.fetchStaffList();
    }

    // Update staff
    @PutMapping("/staff/{id}")
    public Staff updateStaff(
            @RequestBody Staff staff,
            @PathVariable("id") Long staffId) {

        return staffService.updateStaff(staff, staffId);
    }

    // Delete staff
    // Delete staff
    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Map<String, String>> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Staff deleted successfully");

        return ResponseEntity.ok(response);
    }
}