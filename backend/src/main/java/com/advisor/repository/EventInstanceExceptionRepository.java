package com.advisor.repository;

import com.advisor.model.entity.EventInstanceException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("eventInstanceExceptionRepository")
public interface EventInstanceExceptionRepository extends JpaRepository<EventInstanceException, UUID> {

}
