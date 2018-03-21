package com.advisor.service;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.Training;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import com.advisor.model.request.TrainListRequest;
import com.advisor.service.Exceptions.TrainNotFoundException;

import java.util.List;
import java.util.UUID;

public interface TrainService {

    void addTrainList(User user, TrainListRequest trainListRequest);

    Train findByCreatorAndId(User user, UUID trainId);

    void updateTrain(Train train);

    UserTrain findUserTrainByTrainIdAndUser(Train train, User user);

    void addUserTrain(User user, Train train);

    void useTrainList(UserTrain userTrain);

    Train findTrainById(UUID trainId);

    void setStatus(User user, UUID trainId, String status) throws TrainNotFoundException;

    List<Train> getAllTrainLists(User user);

    void removeTrain(UserTrain userTrain);

    Train findTrainByUserAndTrainId(User user, UUID trainId) throws TrainNotFoundException;

    List<Train> getAllTrainings(User user);

    List<Train> getAllActiveTrainings(User user);
}
