package com.advisor.service;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import com.advisor.repository.UserTrainRepository;
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

@Service("userTrainService")
public class UserTrainServiceImpl implements UserTrainService {

    private static final String USER_TRAIN_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    private static final String USER_TRAIN_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    @Autowired
    @Qualifier("userTrainRepository")
    private UserTrainRepository repository;

    @Override
    public UserTrain create(UserTrain userTrain) throws EntityExists {
        if(userTrain.getId() == null || !repository.existsById(userTrain.getId())) {
            return repository.save(userTrain);
        } else {
            throw new EntityExists(USER_TRAIN_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(USER_TRAIN_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<UserTrain> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserTrain> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public UserTrain update(UserTrain userTrain) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(userTrain.getId())){
            return repository.save(userTrain);
        } else {
            throw new EntityNotFoundException(USER_TRAIN_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<UserTrain> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public List<UserTrain> findByUserAndTrain(User user, Train train) {
        return repository.findByUserAndTrain(user, train);
    }
}
