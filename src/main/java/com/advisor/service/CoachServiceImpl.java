package com.advisor.service;

import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import com.advisor.repository.CoachingRepository;
import com.advisor.service.Exceptions.CoachingNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("CoachService")
public class CoachServiceImpl implements CoachService {

    @Autowired
    @Qualifier("coachingRepository")
    private CoachingRepository coachingRepository;

    @Override
    public Coaching findByCoachAndClient(User coach, User client) throws CoachingNotFoundException{
        Coaching coachingList = coachingRepository.findByCoachAndClient(coach, client);
        return coachingList;
    }

    @Override
    public void addNewCoaching(User coach, User client) {

        Coaching coaching = new Coaching(coach, client, "sent");
        coachingRepository.save(coaching);
    }

    @Override
    public List<Coaching> findByClient(User client) {
        return coachingRepository.findByClient(client);
    }

    @Override
    public List<Coaching> findByCoach(User coach) {
        return coachingRepository.findByCoach(coach);
    }
}
