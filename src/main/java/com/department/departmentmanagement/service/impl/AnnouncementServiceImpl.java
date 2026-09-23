package com.department.departmentmanagement.service.impl;

import com.department.departmentmanagement.entity.Announcement;
import com.department.departmentmanagement.repository.AnnouncementRepository;
import com.department.departmentmanagement.service.AnnouncementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementServiceImpl(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }

    @Override
    public Announcement saveAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        return announcementRepository.findAll();
    }


    @Override
    public void deleteAnnouncement(Long id) {
        announcementRepository.deleteById(id);
    }
    @Override
    public Announcement updateAnnouncement(Long id, Announcement announcement) {

        Announcement existingAnnouncement =
                announcementRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Announcement not found"));

        existingAnnouncement.setTitle(announcement.getTitle());
        existingAnnouncement.setMessage(announcement.getMessage());
        existingAnnouncement.setDate(announcement.getDate());

        return announcementRepository.save(existingAnnouncement);
    }
}