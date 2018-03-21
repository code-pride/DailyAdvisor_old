package com.advisor.repository;

import com.advisor.model.entity.CoachType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository("coachTypeRepository")
public interface CoachTypeRepository extends JpaRepository<CoachType, UUID> {

    CoachType findByType(String type);
}
