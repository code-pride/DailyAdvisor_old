package com.advisor.service;

import com.advisor.model.entity.RecurringType;
import com.advisor.repository.RecurringTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("eventService")
public class EventServiceImpl implements EventService {

    @Autowired
    @Qualifier("recurringTypeRepository")
    private RecurringTypeRepository recurringTypeRepository;

    @Override
    public RecurringType findByRecurringName(String type) {
        return recurringTypeRepository.findByRecurringName(type);
    }
}
