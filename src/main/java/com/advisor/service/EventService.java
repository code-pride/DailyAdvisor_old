package com.advisor.service;

import com.advisor.model.entity.RecurringType;

public interface EventService {

    RecurringType findByRecurringName(String type);
}
