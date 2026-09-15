package com.department.departmentmanagement.service.impl;

import com.department.departmentmanagement.entity.Department;
import com.department.departmentmanagement.repository.DepartmentRepository;
import com.department.departmentmanagement.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department, Long departmentId) {

        Department depDB = departmentRepository.findById(departmentId).get();

        if (department.getName() != null &&
                !"".equalsIgnoreCase(department.getName())) {
            depDB.setName(department.getName());
        }

        if (department.getHod() != null &&
                !"".equalsIgnoreCase(department.getHod())) {
            depDB.setHod(department.getHod());
        }

        if (department.getLocation() != null &&
                !"".equalsIgnoreCase(department.getLocation())) {
            depDB.setLocation(department.getLocation());
        }

        return departmentRepository.save(depDB);

    }
    @Override
    public void deleteDepartmentById(Long departmentId) {

        departmentRepository.deleteById(departmentId);

    }
}