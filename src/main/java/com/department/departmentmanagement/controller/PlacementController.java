package com.department.departmentmanagement.controller;

import com.department.departmentmanagement.entity.Placement;
import com.department.departmentmanagement.service.PlacementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placements")
public class PlacementController {

    private final PlacementService placementService;

    public PlacementController(PlacementService placementService) {
        this.placementService = placementService;
    }

    // Add placement
    @PostMapping
    public Placement addPlacement(@RequestBody Placement placement) {
        return placementService.savePlacement(placement);
    }

    // Get all placements
    @GetMapping
    public List<Placement> getAllPlacements() {
        return placementService.getAllPlacements();
    }

    // Delete placement
    @DeleteMapping("/{id}")
    public String deletePlacement(@PathVariable Long id) {
        placementService.deletePlacement(id);
        return "Placement deleted successfully";
    }
}