package com.advisor.service;

import com.advisor.model.entity.Event;
import com.advisor.model.entity.RecurringType;

import java.util.UUID;

public interface EventService extends IService<Event, UUID>{

    RecurringType findByRecurringName(String type);

}
