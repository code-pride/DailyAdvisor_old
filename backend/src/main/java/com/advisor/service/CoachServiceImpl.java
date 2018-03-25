package com.advisor.service;

import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import com.advisor.repository.CoachingRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service("CoachService")
public class CoachServiceImpl implements CoachService {

    private static final String COACHING_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.coaching";

    private static final String COACHING_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.coach";

    @Autowired
    @Qualifier("coachingRepository")
    private CoachingRepository repository;

    @Override
    public Coaching findByCoachAndClient(User coach, User client){
        List<Coaching>  coachingList = repository.findByCoachAndClient(coach, client);
        if(coachingList.size() != 0) {
            return coachingList.get(0);
        }
        else return null;
    }

    @Override
    public List<Coaching> findByClient(User client) {
        return repository.findByClient(client);
    }

    @Override
    public List<Coaching> findByCoach(User coach) {
        return repository.findByCoach(coach);
    }

    @Override
    public Coaching create(Coaching coaching) throws EntityExists {
        if(coaching.getId() == null || repository.existsById(coaching.getId())) {
            return repository.save(coaching);
        } else {
            throw new EntityExists(COACHING_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(COACHING_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Coaching> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Coaching> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Coaching update(Coaching coaching) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(coaching.getId())){
            return repository.save(coaching);
        } else {
            throw new EntityNotFoundException(COACHING_NOT_FOUND_MESSAGE_CODE);
        }
    }

}
