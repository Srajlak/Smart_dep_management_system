package com.department.departmentmanagement.service;

import com.department.departmentmanagement.entity.Placement;

import java.util.List;

public interface PlacementService {

    Placement savePlacement(Placement placement);

    List<Placement> getAllPlacements();

    void deletePlacement(Long id);
}