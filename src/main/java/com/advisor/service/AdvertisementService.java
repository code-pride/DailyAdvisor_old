package com.advisor.service;

import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;

public interface AdvertisementService {
    void setAdvertisement(User user, AdvertisementRequest advertisementRequest);
}
