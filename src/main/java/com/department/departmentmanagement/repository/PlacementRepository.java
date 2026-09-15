package com.department.departmentmanagement.repository;

import com.department.departmentmanagement.entity.Placement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacementRepository extends JpaRepository<Placement, Long> {
}