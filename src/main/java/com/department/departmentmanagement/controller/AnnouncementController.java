package com.department.departmentmanagement.controller;

import com.department.departmentmanagement.entity.Announcement;
import com.department.departmentmanagement.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    @PostMapping
    public Announcement addAnnouncement(
            @RequestBody Announcement announcement) {

        return announcementService.saveAnnouncement(announcement);
    }

    @GetMapping
    public List<Announcement> getAnnouncements() {

        return announcementService.getAllAnnouncements();
    }

    @DeleteMapping("/{id}")
    public String deleteAnnouncement(
            @PathVariable Long id) {

        announcementService.deleteAnnouncement(id);

        return "Announcement deleted successfully";
    }
}