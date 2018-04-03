package com.advisor.repository;

import com.advisor.model.entity.RecurringType;
import org.springframework.stereotype.Repository;

@Repository("recurringTypeRepository")
public interface RecurringTypeRepository extends SimplyRepository<RecurringType> {

    RecurringType findByRecurringName(String recurringName);
}
