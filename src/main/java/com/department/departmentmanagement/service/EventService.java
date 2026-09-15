package com.department.departmentmanagement.service;

import com.department.departmentmanagement.entity.Event;

import java.util.List;

public interface EventService {

    Event saveEvent(Event event);

    List<Event> getAllEvents();

    void deleteEvent(Long id);
}