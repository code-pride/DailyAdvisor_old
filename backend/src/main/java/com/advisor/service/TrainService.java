package com.advisor.service;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import com.advisor.model.request.TrainListRequest;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface TrainService extends IService<Train, UUID> {

    void addTrainList(User user, TrainListRequest trainListRequest) throws DataRepositoryException;

    Train findByCreatorAndId(User user, UUID trainId);

    Train update(Train train) throws DataRepositoryException;

    UserTrain findUserTrainByTrainIdAndUser(Train train, User user);

    void addUserTrain(User user, Train train) throws DataRepositoryException;

    void useTrainList(UserTrain userTrain) throws DataRepositoryException;

    Train findTrainById(UUID trainId);

    void setStatus(User user, UUID trainId, String status) throws EntityNotFoundException;

    List<Train> getAllTrainLists(User user);

    void removeTrain(UserTrain userTrain) throws DataRepositoryException;

    Train findTrainByUserAndTrainId(User user, UUID trainId) throws EntityNotFoundException;

    List<Train> getAllTrainings(User user);

    List<Train> getAllActiveTrainings(User user);
}
