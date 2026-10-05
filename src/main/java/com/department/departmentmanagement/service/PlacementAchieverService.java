package com.department.departmentmanagement.service;

import com.department.departmentmanagement.entity.PlacementAchiever;

import java.util.List;

public interface PlacementAchieverService {

    PlacementAchiever saveAchiever(PlacementAchiever achiever);

    List<PlacementAchiever> getAllAchievers();

    void deleteAchiever(Long id);
}