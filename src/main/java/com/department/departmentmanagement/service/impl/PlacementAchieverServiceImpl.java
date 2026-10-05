package com.department.departmentmanagement.service.impl;

import com.department.departmentmanagement.entity.PlacementAchiever;
import com.department.departmentmanagement.repository.PlacementAchieverRepository;
import com.department.departmentmanagement.service.PlacementAchieverService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlacementAchieverServiceImpl implements PlacementAchieverService {

    private final PlacementAchieverRepository placementAchieverRepository;

    public PlacementAchieverServiceImpl(PlacementAchieverRepository placementAchieverRepository) {
        this.placementAchieverRepository = placementAchieverRepository;
    }

    @Override
    public PlacementAchiever saveAchiever(PlacementAchiever achiever) {
        return placementAchieverRepository.save(achiever);
    }

    @Override
    public List<PlacementAchiever> getAllAchievers() {
        return placementAchieverRepository.findAll();
    }

    @Override
    public void deleteAchiever(Long id) {
        placementAchieverRepository.deleteById(id);
    }
}