package com.advisor.repository;

import com.advisor.model.entity.RecurringType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("recurringTypeRepository")
public interface RecurringTypeRepository extends JpaRepository<RecurringType, UUID> {


    RecurringType findByRecurringName(String type);
}
