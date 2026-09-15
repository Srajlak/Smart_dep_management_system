package com.department.departmentmanagement.service;

import com.department.departmentmanagement.entity.Staff;

import java.util.List;

public interface StaffService {

    Staff saveStaff(Staff staff);

    List<Staff> fetchStaffList();

    Staff updateStaff(Staff staff, Long staffId);

    void deleteStaffById(Long staffId);
    void deleteStaff(Long id);
}