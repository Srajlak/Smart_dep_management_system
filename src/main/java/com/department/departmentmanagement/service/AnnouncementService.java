package com.department.departmentmanagement.service;

import com.department.departmentmanagement.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    Announcement saveAnnouncement(Announcement announcement);

    List<Announcement> getAllAnnouncements();

    void deleteAnnouncement(Long id);
}