package com.department.departmentmanagement.controller;

import com.department.departmentmanagement.entity.PlacementAchiever;
import com.department.departmentmanagement.service.PlacementAchieverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/placement-achievers")
public class PlacementAchieverController {

    private final PlacementAchieverService service;

    public PlacementAchieverController(PlacementAchieverService service) {
        this.service = service;
    }

    @PostMapping
    public PlacementAchiever addAchiever(@RequestBody PlacementAchiever achiever) {
        return service.saveAchiever(achiever);
    }

    @GetMapping
    public List<PlacementAchiever> getAllAchievers() {
        return service.getAllAchievers();
    }

    @DeleteMapping("/{id}")
    public String deleteAchiever(@PathVariable Long id) {
        service.deleteAchiever(id);
        return "Placement achiever deleted successfully";
    }
}