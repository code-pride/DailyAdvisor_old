package com.advisor.repository;

import com.advisor.model.entity.CoachType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("coachTypeRepository")
public interface CoachTypeRepository extends JpaRepository<CoachType, Integer> {

    CoachType findByType(String type);
}
