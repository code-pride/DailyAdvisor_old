package com.advisor.service;



import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import com.advisor.model.response.UserProfileResponse;
import com.advisor.service.Exceptions.CoachingNotFoundException;

import java.util.List;

public interface CoachService {

    Coaching findByCoachAndClient(User coach, User client)throws CoachingNotFoundException;

    void addNewCoaching(User coach, User client);

    List<Coaching> findByClient(User client);
}
