package com.department.departmentmanagement.controller;

import com.department.departmentmanagement.entity.Student;
import com.department.departmentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import com.department.departmentmanagement.service.ExcelStudentService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExcelStudentService excelStudentService;

    @PostMapping("/students")
    public ResponseEntity<?> saveStudent(
            @RequestBody Student student,
            HttpServletRequest request) {

        String role = request.getHeader("Role");

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body("Only ADMIN can add students");
        }

        return ResponseEntity.ok(studentService.saveStudent(student));
    }

    @GetMapping("/students")
    public List<Student> fetchStudentList() {
        return studentService.fetchStudentList();
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<?> updateStudent(
            @RequestBody Student student,
            @PathVariable("id") Long studentId,
            HttpServletRequest request) {

        String role = request.getHeader("Role");

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body("Only ADMIN can update students");
        }

        return ResponseEntity.ok(
                studentService.updateStudent(student, studentId)
        );
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable("id") Long studentId,
            HttpServletRequest request) {

        String role = request.getHeader("Role");

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body("Only ADMIN can delete students");
        }

        studentService.deleteStudentById(studentId);

        return ResponseEntity.ok(
                "Student deleted successfully"
        );
    }

    @PostMapping("/students/upload")
    public ResponseEntity<String> uploadStudents(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request) {

        String role = request.getHeader("Role");

        if (!"ADMIN".equals(role)) {
            return ResponseEntity.status(403)
                    .body("Only ADMIN can upload students");
        }

        try {
            excelStudentService.uploadStudents(file);

            return ResponseEntity.ok(
                    "Students uploaded successfully"
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Error uploading students: " + e.getMessage());
        }
    }
}