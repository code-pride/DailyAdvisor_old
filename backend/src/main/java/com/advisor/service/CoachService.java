package com.advisor.service;

import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import com.advisor.service.Exceptions.EntityExists;

import java.util.List;
import java.util.UUID;

public interface CoachService extends IService<Coaching, UUID>{

    Coaching findByCoachAndClient(User coach, User client);

    Coaching create(Coaching coaching) throws EntityExists;

    List<Coaching> findByClient(User client);

    List<Coaching> findByCoach(User user);

}
