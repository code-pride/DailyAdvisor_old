package com.advisor.repository;

import com.advisor.model.entity.RecurringPattern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("recurringPatternRepository")
public interface RecurringPatternRepository extends JpaRepository<RecurringPattern, Integer> {

}
