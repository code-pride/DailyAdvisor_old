package com.advisor.repository;


import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("coachingRepository")
public interface CoachingRepository extends SimplyRepository<Coaching> {

    List<Coaching> findByCoach(User coach);

    List<Coaching> findByClient(User client);

    //TODO delete query
    @Query("SELECT c FROM Coaching c WHERE c.coach = :coach AND c.client = :client")
    List<Coaching>  findByCoachAndClient(@Param("coach") User coach, @Param("client") User client);

}
