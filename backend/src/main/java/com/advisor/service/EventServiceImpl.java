package com.advisor.service;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.RecurringType;
import com.advisor.repository.EventRepository;
import com.advisor.repository.RecurringTypeRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("eventService")
public class EventServiceImpl implements EventService {

    private static final String EVENT_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.event";

    private static final String EVENT_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.event";

    @Autowired
    @Qualifier("recurringTypeRepository")
    private RecurringTypeRepository recurringTypeRepository;

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository repository;

    @Override
    public Event create(Event event) throws EntityExists {
        if(event.getId() == null || !repository.existsById(event.getId())) {
            return repository.save(event);
        } else {
            throw new EntityExists(EVENT_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(EVENT_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Event> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Event> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Event update(Event event) throws DataRepositoryException {
        if(repository.existsById(event.getId())){
            return repository.save(event);
        } else {
            throw new EntityNotFoundException(EVENT_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public RecurringType findByRecurringName(String type) {
        return recurringTypeRepository.findByRecurringName(type);
    }
}
