package com.advisor.service;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import com.advisor.model.request.TrainListRequest;

public interface TrainService {


    void addTrainList(User user, TrainListRequest trainListRequest);

    Train findByCreatorAndId(User user, long trainId);

    void updateTrain(Train train);

    UserTrain findUserTrainByTrainIdAndUser(Train train, User user);

    void addUserTrain(User user, Train train);

    void useTrainList(UserTrain userTrain);

    Train findTrainById(long trainId);
}
