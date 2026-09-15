package com.department.departmentmanagement.service;

import com.department.departmentmanagement.entity.Department;
import com.department.departmentmanagement.entity.Student;
import com.department.departmentmanagement.repository.DepartmentRepository;
import com.department.departmentmanagement.repository.StudentRepository;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class ExcelStudentService {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;

    public ExcelStudentService(StudentRepository studentRepository,
                               DepartmentRepository departmentRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
    }

    public void uploadStudents(MultipartFile file) throws Exception {

        InputStream inputStream = file.getInputStream();

        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        // Get MCA Department with ID 1
        Department department = departmentRepository.findById(1L)
                .orElseThrow(() ->
                        new RuntimeException("Department with ID 1 not found"));

        for (Row row : sheet) {

            // Skip the first row because it contains column headings
            if (row.getRowNum() == 0) {
                continue;
            }

            Student student = new Student();

            // Assign the student to Department ID 1
            student.setDepartment(department);

            // Read student details from Excel
            student.setRegisterNumber(
                    getCellValue(row.getCell(0))
            );

            student.setName(
                    getCellValue(row.getCell(1))
            );

            student.setEmail(
                    getCellValue(row.getCell(2))
            );

            student.setPhone(
                    getCellValue(row.getCell(3))
            );

            String yearValue = getCellValue(row.getCell(4));

            if (!yearValue.isEmpty()) {
                student.setYear(
                        Integer.parseInt(yearValue)
                );
            }

            student.setSection(
                    getCellValue(row.getCell(5))
            );

            // Save student to database
            studentRepository.save(student);
        }

        workbook.close();
        inputStream.close();
    }

    private String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell).trim();
    }
}