package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.request.TrainListRequest;

public interface TrainService {


    void addTrainList(User user, TrainListRequest trainListRequest);
}
