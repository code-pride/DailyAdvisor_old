package com.advisor.service;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;

import java.util.List;
import java.util.UUID;

public interface UserTrainService extends IService<UserTrain, UUID> {
    List<UserTrain> findByUser(User user);

    List<UserTrain> findByUserAndTrain(User user, Train train);
}
