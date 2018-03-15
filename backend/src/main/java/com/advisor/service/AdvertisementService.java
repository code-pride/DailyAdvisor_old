package com.advisor.service;

import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.response.AdvertisementResponse;
import com.advisor.service.Exceptions.AdvertisementNotFound;

import java.util.List;

public interface AdvertisementService extends IService<Advertisement, Long>{

    void setAdvertisement(User user, AdvertisementRequest advertisementRequest);

    List<AdvertisementResponse> selectAll();

    AdvertisementResponse findByUser(User user);

    Advertisement findActiveAdvertisementByUser(User user);

    void updateAdvertisement(User user, AdvertisementRequest advertisementRequest);

    void updateStatus(long advId, User user, String status) throws AdvertisementNotFound;

    List<Advertisement> getByCriteria(List<User> user, String coachType);

}
