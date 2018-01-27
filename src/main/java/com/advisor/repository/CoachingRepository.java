package com.advisor.repository;


import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("coachingRepository")
public interface CoachingRepository extends JpaRepository<Coaching, Integer> {

    Coaching findByCoachAndClient(User coach, User client);

    Coaching findByCoach(User coach);

    List<Coaching> findByClient(User client);


}
