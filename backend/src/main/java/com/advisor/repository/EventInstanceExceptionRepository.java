package com.advisor.repository;

import com.advisor.model.entity.EventInstanceException;
import org.springframework.stereotype.Repository;

@Repository("eventInstanceExceptionRepository")
public interface EventInstanceExceptionRepository extends SimplyRepository<EventInstanceException> {

}
