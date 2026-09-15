package com.department.departmentmanagement.service.impl;

import com.department.departmentmanagement.entity.Staff;
import com.department.departmentmanagement.repository.StaffRepository;
import com.department.departmentmanagement.service.StaffService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public Staff saveStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public List<Staff> fetchStaffList() {
        return staffRepository.findAll();
    }

    @Override
    public Staff updateStaff(Staff staff, Long staffId) {

        Optional<Staff> existingStaff = staffRepository.findById(staffId);

        if (existingStaff.isPresent()) {

            Staff updatedStaff = existingStaff.get();

            updatedStaff.setStaffName(staff.getStaffName());
            updatedStaff.setEmail(staff.getEmail());
            updatedStaff.setPhone(staff.getPhone());
            updatedStaff.setDesignation(staff.getDesignation());
            updatedStaff.setUsername(staff.getUsername());
            updatedStaff.setPassword(staff.getPassword());
            updatedStaff.setRole(staff.getRole());

            return staffRepository.save(updatedStaff);
        }

        return null;
    }

    @Override
    public void deleteStaffById(Long staffId) {
        staffRepository.deleteById(staffId);
    }
    @Override
    public void deleteStaff(Long id) {
        staffRepository.deleteById(id);
    }
}