package com.advisor.repository;

import com.advisor.model.entity.CoachType;
import org.springframework.stereotype.Repository;


@Repository("coachTypeRepository")
public interface CoachTypeRepository extends SimplyRepository<CoachType> {

    CoachType findByType(String type);
}
