package com.department.departmentmanagement.service.impl;

import com.department.departmentmanagement.entity.Placement;
import com.department.departmentmanagement.repository.PlacementRepository;
import com.department.departmentmanagement.service.PlacementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementServiceImpl implements PlacementService {

    private final PlacementRepository placementRepository;

    public PlacementServiceImpl(PlacementRepository placementRepository) {
        this.placementRepository = placementRepository;
    }

    @Override
    public Placement savePlacement(Placement placement) {
        return placementRepository.save(placement);
    }

    @Override
    public List<Placement> getAllPlacements() {
        return placementRepository.findAll();
    }

    @Override
    public void deletePlacement(Long id) {
        placementRepository.deleteById(id);
    }
}