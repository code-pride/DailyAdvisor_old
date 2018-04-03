package com.advisor.service;

import com.advisor.model.entity.RecurringType;

import java.util.UUID;

public interface RecurringTypeService extends IService<RecurringType, UUID> {
    RecurringType findByRecurringName(String recurringName);
}
