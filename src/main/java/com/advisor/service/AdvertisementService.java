package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.response.AdvertisementResponse;

import java.util.List;

public interface AdvertisementService {
    void setAdvertisement(User user, AdvertisementRequest advertisementRequest);


    List<AdvertisementResponse> selectAll();

    AdvertisementResponse getAdvertisementByUser(User user);

    void updateAdvertisement(User user, AdvertisementRequest advertisementRequest);
}
