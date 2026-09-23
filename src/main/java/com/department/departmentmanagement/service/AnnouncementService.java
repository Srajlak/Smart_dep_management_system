package com.department.departmentmanagement.service;

import com.department.departmentmanagement.entity.Announcement;

import java.util.List;

public interface AnnouncementService {

    Announcement saveAnnouncement(Announcement announcement);

    List<Announcement> getAllAnnouncements();

    Announcement updateAnnouncement(Long id, Announcement announcement);

    void deleteAnnouncement(Long id);
}