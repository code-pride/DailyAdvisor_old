package com.advisor.repository;


import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("coachingRepository")
public interface CoachingRepository extends JpaRepository<Coaching, UUID> {

    List<Coaching> findByCoach(User coach);

    List<Coaching> findByClient(User client);

    //TODO delte query
    @Query("SELECT c FROM Coaching c WHERE c.coach = :coach AND c.client = :client")
    List<Coaching>  findByCoachAndClient(@Param("coach") User coach, @Param("client") User client);

}
