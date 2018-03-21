package com.advisor.service;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.Training;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import com.advisor.model.request.TrainListRequest;
import com.advisor.repository.*;
import com.advisor.service.Exceptions.TrainNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("trainService")
public class TrainServiceImpl implements TrainService {

    @Autowired
    @Qualifier("trainRepository")
    private TrainRepository trainRepository;

    @Autowired
    @Qualifier("userTrainRepository")
    private UserTrainRepository userTrainRepository;

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository eventRepository;

    @Autowired
    @Qualifier("recurringPatternRepository")
    private RecurringPatternRepository recurringPatternRepository;

    @Autowired
    @Qualifier("recurringTypeRepository")
    private RecurringTypeRepository recurringTypeRepository;

    @Override
    public void addTrainList(User user, TrainListRequest trainListRequest) {
        Train trainList = new Train(user, trainListRequest);

        for (Training training : trainList.getTrainings()) {
            if(training.getEvent().getRecurring()){
                training.getEvent().getRecurringPattern().setRecurringType(recurringTypeRepository.findByRecurringName(training.getEvent().getRecurringPattern().getRecurringType().getRecurringName()));
            }
            else {
                training.getEvent().setRecurringPattern(null);
            }
            recurringPatternRepository.save(training.getEvent().getRecurringPattern());
            eventRepository.save(training.getEvent());
        }
        trainRepository.save(trainList);
    }

    @Override
    public Train findByCreatorAndId(User user, UUID trainId) {
        List<Train> trainList = trainRepository.findByCreatorAndId(user, trainId);
        if(trainList.size()>0){
            return trainList.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public void updateTrain(Train train) {
        trainRepository.save(train);
    }

    @Override
    public UserTrain findUserTrainByTrainIdAndUser(Train train, User user) {
        List<UserTrain> userTrains = userTrainRepository.findByUserAndTrain(user, train);
        if(userTrains.size()!=0){
            return userTrains.get(0);
        }
        return null;
    }

    @Override
    public void addUserTrain(User user, Train train) {
        userTrainRepository.save(new UserTrain(user, train, "waiting"));
    }

    @Override
    public void useTrainList(UserTrain userTrain) {
        userTrain.setStatus("used");
        userTrainRepository.save(userTrain);
    }

    @Override
    public Train findTrainById(UUID trainId) {
        return trainRepository.findOneById(trainId);
    }

    @Override
    public void setStatus(User user, UUID trainId, String status) throws TrainNotFoundException {
        Train train = findByCreatorAndId(user, trainId);
        if(train != null && train.getStatus().equals("published")){
            train.setStatus(status);
            trainRepository.save(train);
        } else {
            throw new TrainNotFoundException();
        }
    }

    @Override
    public List<Train> getAllTrainLists(User user) {
        List<Train> trainList = trainRepository.findByCreatedBy(user);
        List<UserTrain> trainList2 = userTrainRepository.findByUser(user);
        for (UserTrain userTrain : trainList2) {
            if(!trainList.contains(userTrain.getTrain())){
                trainList.add(userTrain.getTrain());
            }
        }
        return trainList;
    }

    @Override
    public void removeTrain(UserTrain userTrain) {
        userTrain.setStatus("waiting");
        userTrainRepository.save(userTrain);
    }

    @Override
    public Train findTrainByUserAndTrainId(User user, UUID trainId) throws TrainNotFoundException{
        Train train = trainRepository.findOneById(trainId);
        if(train != null) {
            if (train.getCreatedBy().equals(user)) {
                return train;
            } else {
                List<UserTrain> userTrains = userTrainRepository.findByUserAndTrain(user, train);
                if (userTrains.size() == 1) {
                    return userTrains.get(0).getTrain();
                }
            }
        }
        throw new TrainNotFoundException();
    }

    @Override
    public List<Train> getAllTrainings(User user) {
        List<UserTrain> userTrains = userTrainRepository.findByUser(user);
        List<Train> trainList = new ArrayList<>();
        for (UserTrain userTrain : userTrains) {
            trainList.add(userTrain.getTrain());
        }
        return trainList;
    }

    @Override
    public List<Train> getAllActiveTrainings(User user) {
        List<UserTrain> userTrains = userTrainRepository.findByUser(user);
        List<Train> trainList = new ArrayList<>();
        for (UserTrain userTrain : userTrains) {
            if(userTrain.getStatus().equals("used")) {
                trainList.add(userTrain.getTrain());
            }
        }
        return trainList;
    }
}
