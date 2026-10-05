package com.department.departmentmanagement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "placement_achievers")
public class PlacementAchiever {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentName;
    private String studentClass;
    private String companyName;
    private String placementYear;

    private String photoUrl;

    public PlacementAchiever() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPlacementYear() {
        return placementYear;
    }

    public void setPlacementYear(String placementYear) {
        this.placementYear = placementYear;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}