package com.advisor.repository;

import com.advisor.model.entity.Event;
import org.springframework.stereotype.Repository;

@Repository("eventRepository")
public interface EventRepository extends SimplyRepository<Event> {

}
