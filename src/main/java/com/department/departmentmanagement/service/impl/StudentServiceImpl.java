package com.department.departmentmanagement.service.impl;

import com.department.departmentmanagement.entity.Student;
import com.department.departmentmanagement.repository.StudentRepository;
import com.department.departmentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> fetchStudentList() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long studentId) {

        Student studentDB = studentRepository.findById(studentId).get();

        if (student.getRegisterNumber() != null &&
                !student.getRegisterNumber().isEmpty()) {
            studentDB.setRegisterNumber(student.getRegisterNumber());
        }

        if (student.getName() != null &&
                !student.getName().isEmpty()) {
            studentDB.setName(student.getName());
        }

        if (student.getEmail() != null &&
                !student.getEmail().isEmpty()) {
            studentDB.setEmail(student.getEmail());
        }

        if (student.getPhone() != null &&
                !student.getPhone().isEmpty()) {
            studentDB.setPhone(student.getPhone());
        }

        if (student.getYear() != null) {
            studentDB.setYear(student.getYear());
        }

        if (student.getSection() != null &&
                !student.getSection().isEmpty()) {
            studentDB.setSection(student.getSection());
        }

        if (student.getPassword() != null &&
                !student.getPassword().isEmpty()) {
            studentDB.setPassword(student.getPassword());
        }

        if (student.getDepartment() != null) {
            studentDB.setDepartment(student.getDepartment());
        }

        return studentRepository.save(studentDB);
    }

    @Override
    public void deleteStudentById(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}