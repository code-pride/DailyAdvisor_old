package com.advisor.service;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.Training;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import com.advisor.model.request.TrainListRequest;
import com.advisor.repository.*;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import com.advisor.service.Exceptions.TrainNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;


@Service("trainService")
public class TrainServiceImpl implements TrainService {

    private static final String TRAIN_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    private static final String TRAIN_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    @Autowired
    @Qualifier("trainRepository")
    private TrainRepository repository;

    @Autowired
    @Qualifier("eventService")
    private EventService eventService;

    @Autowired
    @Qualifier("recurringPatternService")
    private RecurringPatternService recurringPatternService;

    @Autowired
    @Qualifier("recurringTypeService")
    private RecurringTypeService recurringTypeService;

    @Autowired
    @Qualifier("userTrainService")
    private UserTrainService userTrainService;

    @Override
    public Train create(Train train) throws EntityExists {
        if(train.getId() == null || !repository.existsById(train.getId())) {
            return repository.save(train);
        } else {
            throw new EntityExists(TRAIN_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(TRAIN_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Train> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Train> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Train update(Train train) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(train.getId())){
            return repository.save(train);
        } else {
            throw new EntityNotFoundException(TRAIN_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public void addTrainList(User user, TrainListRequest trainListRequest) throws DataRepositoryException {
        Train trainList = new Train(user, trainListRequest);

        for (Training training : trainList.getTrainings()) {
            if(training.getEvent().getRecurring()){
                training.getEvent().getRecurringPattern().setRecurringType(recurringTypeService.findByRecurringName(training.getEvent().getRecurringPattern().getRecurringType().getRecurringName()));
            }
            else {
                training.getEvent().setRecurringPattern(null);
            }
            recurringPatternService.create(training.getEvent().getRecurringPattern());
            eventService.create(training.getEvent());
        }
        repository.save(trainList);
    }

    @Override
    public Train findByCreatorAndId(User user, UUID trainId) {
        List<Train> trainList = repository.findByCreatorAndId(user, trainId);
        if(trainList.size()>0){
            return trainList.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public UserTrain findUserTrainByTrainIdAndUser(Train train, User user) {
        List<UserTrain> userTrains = userTrainService.findByUserAndTrain(user, train);
        if(userTrains.size()!=0){
            return userTrains.get(0);
        }
        return null;
    }


    //TODO move to controller creation of UserTrain
    @Override
    public void addUserTrain(User user, Train train) throws DataRepositoryException {
        userTrainService.create(new UserTrain(user, train, "waiting"));
    }

    @Override
    public void useTrainList(UserTrain userTrain) throws DataRepositoryException {
        userTrain.setStatus("used");
        userTrainService.update(userTrain);
    }

    @Override
    public Train findTrainById(UUID trainId) {
        return repository.findOneById(trainId);
    }

    @Override
    public void setStatus(User user, UUID trainId, String status) throws TrainNotFoundException {
        Train train = findByCreatorAndId(user, trainId);
        if(train != null && train.getStatus().equals("published")){
            train.setStatus(status);
            repository.save(train);
        } else {
            throw new TrainNotFoundException();
        }
    }

    @Override
    public List<Train> getAllTrainLists(User user) {
        List<Train> trainList = repository.findByCreatedBy(user);
        List<UserTrain> trainList2 = userTrainService.findByUser(user);
        for (UserTrain userTrain : trainList2) {
            if(!trainList.contains(userTrain.getTrain())){
                trainList.add(userTrain.getTrain());
            }
        }
        return trainList;
    }

    @Override
    public void removeTrain(UserTrain userTrain) throws DataRepositoryException {
        userTrain.setStatus("waiting");
        userTrainService.update(userTrain);
    }

    @Override
    public Train findTrainByUserAndTrainId(User user, UUID trainId) throws TrainNotFoundException{
        Train train = repository.findOneById(trainId);
        if(train != null) {
            if (train.getCreatedBy().equals(user)) {
                return train;
            } else {
                List<UserTrain> userTrains = userTrainService.findByUserAndTrain(user, train);
                if (userTrains.size() == 1) {
                    return userTrains.get(0).getTrain();
                }
            }
        }
        throw new TrainNotFoundException();
    }

    @Override
    public List<Train> getAllTrainings(User user) {
        List<UserTrain> userTrains = userTrainService.findByUser(user);
        List<Train> trainList = new ArrayList<>();
        for (UserTrain userTrain : userTrains) {
            trainList.add(userTrain.getTrain());
        }
        return trainList;
    }

    @Override
    public List<Train> getAllActiveTrainings(User user) {
        List<UserTrain> userTrains = userTrainService.findByUser(user);
        List<Train> trainList = new ArrayList<>();
        for (UserTrain userTrain : userTrains) {
            if(userTrain.getStatus().equals("used")) {
                trainList.add(userTrain.getTrain());
            }
        }
        return trainList;
    }
}
