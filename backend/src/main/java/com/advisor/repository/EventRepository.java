package com.advisor.repository;

import com.advisor.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("eventRepository")
public interface EventRepository extends JpaRepository<Event, UUID> {

}
